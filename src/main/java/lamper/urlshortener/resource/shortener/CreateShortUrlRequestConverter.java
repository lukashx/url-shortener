package lamper.urlshortener.resource.shortener;

import lamper.urlshortener.service.shortener.ShortUrl;

public class CreateShortUrlRequestConverter {

    public static ShortUrl convert(CreateShortUrlRequest createShortUrlRequest) {
        return ShortUrl.builder()
            .fullUrl(createShortUrlRequest.getUrl())
            .redirectType(createShortUrlRequest.getRedirectType())
            .build();
    }

}
