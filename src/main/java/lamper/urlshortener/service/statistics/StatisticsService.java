package lamper.urlshortener.service.statistics;

import lamper.urlshortener.persistence.shortener.ShortUrlReposiotry;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StatisticsService {

    private ShortUrlReposiotry shortUrlReposiotry;
    
    @Autowired
    public StatisticsService(ShortUrlReposiotry shortUrlReposiotry) {
        this.shortUrlReposiotry = shortUrlReposiotry;
    }

    // TODO: Finish statistics service
    public HashMap<String, Integer> retrieveStatistics(String accountId) {
        val statistics = new HashMap<String, Integer>();
        statistics.put("http://myweb.com/something1/somethingelse1/", 5);
        return statistics;
    }

}
