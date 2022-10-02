package lamper.urlshortener.service.shortener;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

@Builder
@Getter
@With
public class ShortUrl {

    String accountId;
    String fullUrl;
    String shortUrlId;
    Integer redirectType;

}
