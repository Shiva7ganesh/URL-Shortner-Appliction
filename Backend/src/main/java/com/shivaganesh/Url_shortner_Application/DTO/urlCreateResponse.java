package com.shivaganesh.Url_shortner_Application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class urlCreateResponse {
    private boolean error;
    private String message;
    private String url;
//    private String token;
}
