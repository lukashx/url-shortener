package lamper.urlshortener.resource.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import lamper.urlshortener.persistence.account.AccountEntity;
import lamper.urlshortener.persistence.account.AccountReposiotry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class AccountResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountReposiotry accountReposiotry;

    @Test
    void createAccountCreatesAccount() throws Exception {
        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
            .accountId("accountId")
            .build();
        mockMvc.perform(post("/account")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createAccountRequest)))
            .andExpect(status().isCreated());

        assertThat(accountReposiotry.findByAccountId("accountId")).isNotNull();
    }

    @Test
    void createAccountReturnsConflictForAlreadyExistingAccount() throws Exception {
        accountReposiotry.save(AccountEntity.builder().accountId("accountId").password("").build());

        CreateAccountRequest accountRequest = CreateAccountRequest.builder().accountId("accountId").build();
        mockMvc.perform(post("/account")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(accountRequest)))
            .andExpect(status().isConflict());
    }
}
