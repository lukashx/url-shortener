package lamper.urlshortener.service.common;

import java.util.UUID;

public class RandomStringGenerator {

    public static String generate(int length) {
        return UUID.randomUUID().toString().replace("-", "").substring(0, length);
    }

}
