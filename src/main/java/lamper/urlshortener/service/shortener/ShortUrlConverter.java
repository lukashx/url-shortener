package lamper.urlshortener.service.shortener;

import lamper.urlshortener.persistence.shortener.ShortUrlEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ShortUrlConverter {

    public static ShortUrl fromPersistence(ShortUrlEntity shortUrlEntity) {
        return ShortUrl.builder()
            .accountId(shortUrlEntity.getAccountId())
            .shortUrlId(shortUrlEntity.getShortUrlId())
            .fullUrl(shortUrlEntity.getFullUrl())
            .redirectType(shortUrlEntity.getRedirectType())
            .build();
    }

    public static ShortUrlEntity toPersistence(ShortUrl shortUrl) {
        return ShortUrlEntity.builder()
            .accountId(shortUrl.getShortUrlId())
            .shortUrlId(shortUrl.getShortUrlId())
            .fullUrl(shortUrl.getFullUrl())
            .redirectType(shortUrl.getRedirectType())
            .build();
    }

}
