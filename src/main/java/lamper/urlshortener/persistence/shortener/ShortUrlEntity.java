package lamper.urlshortener.persistence.shortener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "short_urls")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShortUrlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;

    String accountId;

    String fullUrl;

    String shortUrlId;

    Integer redirectType;

    Integer usedCount;

    public void increaseUsedCount() {
        if (usedCount == null) {
            this.usedCount = 0;
        }
        this.usedCount++;
    }

}
