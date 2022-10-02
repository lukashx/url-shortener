package lamper.urlshortener.security;

import lamper.urlshortener.persistence.account.AccountReposiotry;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationManager {

    private AccountReposiotry accountReposiotry;

    @Autowired
    public CustomAuthenticationProvider(AccountReposiotry accountReposiotry) {
        this.accountReposiotry = accountReposiotry;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        val account = accountReposiotry.findByAccountIdAndPassword(name, password);
        if (account == null) {
            throw new BadCredentialsException("Username or password is wrong");
        }

        return new UsernamePasswordAuthenticationToken(
            name, password, new ArrayList<>());
    }

}
