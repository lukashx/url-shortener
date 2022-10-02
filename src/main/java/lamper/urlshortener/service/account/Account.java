package lamper.urlshortener.service.account;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Account {

    String id;
    String password;

}
