package lamper.urlshortener.resource.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@With
@JsonInclude(NON_EMPTY)
public class CreateAccountResponse {
    private boolean success;
    private String description;
    private String password;
}
