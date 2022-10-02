package lamper.urlshortener.resource.account;

import lamper.urlshortener.service.account.AccountExistsException;
import lamper.urlshortener.service.account.AccountService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class AccountResource {

    private AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/account")
    ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        val createdAccount = accountService.createAccount(createAccountRequest.getAccountId());
        val response = CreateAccountResponseConverter
            .convert(createdAccount)
            .withSuccess(true)
            .withDescription("Your account is opened");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ExceptionHandler(AccountExistsException.class)
    public final ResponseEntity<CreateAccountResponse> handleAccountExistsException(AccountExistsException ex, WebRequest request) {
        val errorResponse = CreateAccountResponse.builder()
            .success(false)
            .description("Account already exists")
            .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
