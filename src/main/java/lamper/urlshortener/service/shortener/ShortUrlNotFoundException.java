package lamper.urlshortener.service.shortener;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShortUrlNotFoundException extends RuntimeException {
}
