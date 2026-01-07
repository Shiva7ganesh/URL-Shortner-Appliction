package com.shivaganesh.Url_shortner_Application.Service;

import com.shivaganesh.Url_shortner_Application.DTO.CreateUrlRequest;
import com.shivaganesh.Url_shortner_Application.DTO.urlResponse;
import com.shivaganesh.Url_shortner_Application.Model.Url;

import java.util.List;
import java.util.Map;

public interface urlService {

    Url createShortUrl(CreateUrlRequest request);

    void editShortUrl(CreateUrlRequest request,String token);

    void deleteShortUrl(Map<String, String> request,String token);

    boolean checkUser(String uid,String shortUrl);

    List<urlResponse> getDashboardData(String uid);

    Url getByShortUrl(String shortUrl);

    void incrementCount(Url url);

    String handleToken(String token,String shorturl);

    String parseToken(String header);
}
