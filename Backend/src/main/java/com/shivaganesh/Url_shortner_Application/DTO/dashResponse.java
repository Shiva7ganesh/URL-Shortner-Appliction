package com.shivaganesh.Url_shortner_Application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class dashResponse {

    private boolean error;

    private List<urlResponse> urls;
}
