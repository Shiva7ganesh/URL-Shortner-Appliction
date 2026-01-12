package com.shivaganesh.Url_shortner_Application.Controller;

import com.shivaganesh.Url_shortner_Application.DTO.*;
import com.shivaganesh.Url_shortner_Application.Model.Url;
import com.shivaganesh.Url_shortner_Application.Security.JwtUtil;
import com.shivaganesh.Url_shortner_Application.Service.urlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/url")
public class urlController {

    private final urlService urlservice;

    @Value("${app.domain}")
    String domain;

    @PostMapping("/add")
    public ResponseEntity<?> createUrl(
            @Valid @RequestBody CreateUrlRequest request,
            @RequestHeader(value = "Authorization", required = false) String header
            ){
        String token = urlservice.parseToken(header);
        Url url = urlservice.createShortUrl(request);

        String jwt = urlservice.handleToken(token,request.getShortUrl());



        String shortUrl = domain + url.getShortUrl();

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + jwt)
                .body(new urlCreateResponse(
                        false,
                        "URL Generated Successfully",
                        shortUrl
                ));
    }

    @PostMapping("/edit")
    public ResponseEntity<responseMessage> editUrl(@Valid @RequestBody CreateUrlRequest request,
                                                   @RequestHeader("Authorization") String header
    ){
        String token = urlservice.parseToken(header);
        urlservice.editShortUrl(request,token);
        return ResponseEntity.ok(
                new responseMessage(
                        false,
                        "Edited Successfully"
                ));
    }

    @PostMapping("/delete")
    public ResponseEntity<responseMessage> deleteUrl(@RequestBody Map<String, String> body,
                                                     @RequestHeader("Authorization") String header
    ){
        String token = urlservice.parseToken(header);
        urlservice.deleteShortUrl(body,token);
        return ResponseEntity.ok(
                new responseMessage(
                        false,
                        "Deleted Successfully"
                ));
    }

    @PostMapping("/dashboard")
    public ResponseEntity<dashResponse> getDashBoard(
            @RequestHeader("Authorization") String authHeader){

        String token = urlservice.parseToken(authHeader);
        String uid = JwtUtil.extractUid(token);
        return ResponseEntity.ok(
                new dashResponse(
                        false,
                        urlservice.getDashboardData(uid)
                )
        );
    }

    @GetMapping("/{shorturl}")
    public void redirect(@PathVariable String shorturl,
                         HttpServletResponse response) throws IOException {
        Url url = urlservice.getByShortUrl(shorturl);
        urlservice.incrementCount(url);

        response.sendRedirect(url.getOrgUrl());
    }

}
