package lamper.urlshortener.resource.account;

import lamper.urlshortener.service.account.Account;

public class CreateAccountResponseConverter {

    public static CreateAccountResponse convert(Account account) {
        return CreateAccountResponse.builder()
            .password(account.getPassword())
            .build();
    }

}
