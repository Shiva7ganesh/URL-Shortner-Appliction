package com.shivaganesh.Url_shortner_Application.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateUrlRequest {

    @NotBlank(message = "Short Url Cannot be Blank")
    @Pattern(
            regexp = "^[a-zA-Z0-9]{1,15}$",
            message ="Short URL must be alphanumeric and max 15 characters"
    )
    private String shortUrl;

    @NotBlank(message = "Original URL cannot be empty")
    @URL(message ="Not a Valid URL")
    private String orgUrl;
    private String token;
}
