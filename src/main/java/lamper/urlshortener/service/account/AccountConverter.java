package lamper.urlshortener.service.account;

import lamper.urlshortener.persistence.account.AccountEntity;

public class AccountConverter {

    public static Account convert(AccountEntity accountEntity) {
        return Account.builder()
            .id(accountEntity.getAccountId())
            .password(accountEntity.getPassword())
            .build();
    }

}
