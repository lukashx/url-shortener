package lamper.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "lamper")
public class UrlShortenerApplication {

    public static void main(String... args) {
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

}
