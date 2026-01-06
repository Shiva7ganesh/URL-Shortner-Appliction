package com.shivaganesh.Url_shortner_Application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class urlResponse {
    private String orgUrl;
    private String shortUrl;
    private long count;
}
