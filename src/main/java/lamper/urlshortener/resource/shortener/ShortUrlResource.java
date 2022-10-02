package lamper.urlshortener.resource.shortener;

import lamper.urlshortener.service.shortener.ShortUrlService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@RestController
public class ShortUrlResource {

    private ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlResource(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @RequestMapping(value = "/register", produces = "application/json")
    ResponseEntity<CreateShortUrlResponse> createShortUrl(@Valid @RequestBody CreateShortUrlRequest createShortUrlRequest, Principal principal) {
        val shortUrlToCreate = CreateShortUrlRequestConverter.convert(createShortUrlRequest)
            .withAccountId(principal.getName());
        val createdShortUrl = shortUrlService.createShortUrl(shortUrlToCreate);
        val response = CreateShortUrlResponse.builder()
            .shortUrl("http://localhost:8080/" + createdShortUrl.getShortUrlId())
            .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{shortUrlId}", method = RequestMethod.GET)
    public void redirectToFullUrl(@PathVariable String shortUrlId, HttpServletResponse httpServletResponse) {
        val shortUrl = shortUrlService.findFullUrl(shortUrlId);
        httpServletResponse.setHeader("Location", shortUrl.getFullUrl());
        httpServletResponse.setStatus(shortUrl.getRedirectType());
    }

}
