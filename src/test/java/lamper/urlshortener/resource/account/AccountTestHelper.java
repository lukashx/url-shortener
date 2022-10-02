package lamper.urlshortener.resource.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
public class AccountTestHelper {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public CreateAccountResponse createAccount(String accountId) throws Exception {
        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
            .accountId(accountId)
            .build();
        val response = mockMvc.perform(post("/account")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createAccountRequest)))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse().getContentAsString();

        return objectMapper.readValue(response, CreateAccountResponse.class);
    }

}
