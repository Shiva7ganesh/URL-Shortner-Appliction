package com.shivaganesh.Url_shortner_Application.DTO;

import com.shivaganesh.Url_shortner_Application.Model.Url;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class dashResponse {

    private boolean error;

    private List<urlResponse> urls;
}
