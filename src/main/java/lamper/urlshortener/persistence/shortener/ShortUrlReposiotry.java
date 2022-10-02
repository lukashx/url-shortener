package lamper.urlshortener.persistence.shortener;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlReposiotry extends CrudRepository<ShortUrlEntity, Long> {

    ShortUrlEntity findByShortUrlId(String shortUrl);

}
