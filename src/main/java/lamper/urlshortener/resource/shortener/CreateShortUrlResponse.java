package lamper.urlshortener.resource.shortener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@With
public class CreateShortUrlResponse {

    private String shortUrl;

}
