package com.shivaganesh.Url_shortner_Application.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection= "url")
public class Url {

    @Id
    private String id;

    @Indexed(unique = true)
    private String shortUrl;

    private String orgUrl;

    private long count;
}
