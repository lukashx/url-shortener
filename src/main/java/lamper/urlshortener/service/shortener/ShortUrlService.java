package lamper.urlshortener.service.shortener;

import lamper.urlshortener.persistence.shortener.ShortUrlReposiotry;
import lamper.urlshortener.service.common.RandomStringGenerator;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {

    private ShortUrlReposiotry shortUrlReposiotry;

    @Autowired
    public ShortUrlService(ShortUrlReposiotry shortUrlReposiotry) {
        this.shortUrlReposiotry = shortUrlReposiotry;
    }

    public ShortUrl createShortUrl(ShortUrl shortUrl) {
        val shortUrlId = RandomStringGenerator.generate(6);
        val shortUrlEntity = ShortUrlConverter.toPersistence(
            shortUrl.withShortUrlId(shortUrlId)
        );
        val savedShortUrlEntity = shortUrlReposiotry.save(shortUrlEntity);

        return ShortUrlConverter.fromPersistence(savedShortUrlEntity);
    }

    public ShortUrl findFullUrl(String shortUrlId) {
        val shortUrlEntity = shortUrlReposiotry.findByShortUrlId(shortUrlId);
        if (shortUrlEntity == null) {
            throw new ShortUrlNotFoundException();
        }

        shortUrlEntity.increaseUsedCount();
        shortUrlReposiotry.save(shortUrlEntity);

        return ShortUrlConverter.fromPersistence(shortUrlEntity);
    }

}
