package lamper.urlshortener.service.account;

import lamper.urlshortener.persistence.account.AccountEntity;
import lamper.urlshortener.persistence.account.AccountReposiotry;
import lamper.urlshortener.service.common.RandomStringGenerator;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final int PASSWORD_LENGTH = 8;

    private AccountReposiotry accountReposiotry;

    @Autowired
    public AccountService(AccountReposiotry accountReposiotry) {
        this.accountReposiotry = accountReposiotry;
    }

    public Account createAccount(String accountId) {
        val existingAccount = accountReposiotry.findByAccountId(accountId);
        if (existingAccount != null) {
            throw new AccountExistsException();
        }
        val savedAccount = accountReposiotry.save(AccountEntity.builder()
            .accountId(accountId)
            .password(RandomStringGenerator.generate(PASSWORD_LENGTH))
            .build());

        return AccountConverter.convert(savedAccount);
    }

}
