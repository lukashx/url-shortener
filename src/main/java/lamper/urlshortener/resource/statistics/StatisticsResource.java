package lamper.urlshortener.resource.statistics;

import lamper.urlshortener.service.statistics.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatisticsResource {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsResource(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(value = "/statistic/{accountId}", produces = "application/json")
    Map<String, Integer> retrieveStatistics(@PathVariable String accountId) {
        return statisticsService.retrieveStatistics(accountId);
    }

}
