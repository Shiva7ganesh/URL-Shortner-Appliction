package com.shivaganesh.Url_shortner_Application.Repository;

import com.shivaganesh.Url_shortner_Application.Model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends MongoRepository<Url,String> {

    Optional<Url> findByShortUrl(String shortUrl);

    boolean existsByShortUrl(String shortUrl);

    List<Url> findByShortUrlIn(List<String> shortUrls);

    boolean deleteByShortUrl(String shortUrl);
}
