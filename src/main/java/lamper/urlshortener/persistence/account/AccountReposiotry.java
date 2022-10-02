package lamper.urlshortener.persistence.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountReposiotry extends CrudRepository<AccountEntity, Long> {

    AccountEntity findByAccountId(String accountId);

    AccountEntity findByAccountIdAndPassword(String accountId, String password);

}
