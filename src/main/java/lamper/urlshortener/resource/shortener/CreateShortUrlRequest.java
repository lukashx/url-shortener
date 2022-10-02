package lamper.urlshortener.resource.shortener;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CreateShortUrlRequest {

    @JsonProperty("URL")
    @NotNull
    private String url;

    @Min(301)
    @Max(302)
    private Integer redirectType = 302;

}
