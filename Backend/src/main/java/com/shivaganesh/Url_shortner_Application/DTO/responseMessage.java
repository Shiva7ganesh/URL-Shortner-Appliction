package com.shivaganesh.Url_shortner_Application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class responseMessage {
    private boolean error;
    private String message;
}
