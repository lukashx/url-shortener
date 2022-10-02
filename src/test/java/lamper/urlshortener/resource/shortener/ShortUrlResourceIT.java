package lamper.urlshortener.resource.shortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lamper.urlshortener.resource.account.AccountTestHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class ShortUrlResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountTestHelper accountTestHelper;

    @Test
    void registerUrlReturnsShortenedUrl() throws Exception {
        val createdAccount = accountTestHelper.createAccount("accountId");
        CreateShortUrlRequest createShortUrlRequest = CreateShortUrlRequest.builder()
            .url("http://fullurl.com")
            .redirectType(301)
            .build();
        val response = mockMvc.perform(post("/register")
                .with(httpBasic("accountId", createdAccount.getPassword()))
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createShortUrlRequest)))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse().getContentAsString();

        val createShortUrlResponse = objectMapper.readValue(response, CreateShortUrlResponse.class);
        assertThat(createShortUrlResponse.getShortUrl()).startsWith("http://localhost");
    }

    @Test
    void registerUrlReturnsUnauthorizedForEmptyAuthentication() throws Exception {
        CreateShortUrlRequest createShortUrlRequest = CreateShortUrlRequest.builder()
            .url("http://fullurl.com")
            .redirectType(301)
            .build();
        mockMvc.perform(post("/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createShortUrlRequest)))
            .andExpect(status().isUnauthorized());
    }

}
