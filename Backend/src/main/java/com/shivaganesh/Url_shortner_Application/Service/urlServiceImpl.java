package com.shivaganesh.Url_shortner_Application.Service;

import com.shivaganesh.Url_shortner_Application.DTO.CreateUrlRequest;
import com.shivaganesh.Url_shortner_Application.DTO.urlResponse;
import com.shivaganesh.Url_shortner_Application.Model.Url;
import com.shivaganesh.Url_shortner_Application.Model.User;
import com.shivaganesh.Url_shortner_Application.Repository.UrlRepository;
import com.shivaganesh.Url_shortner_Application.Repository.UserRepository;
import com.shivaganesh.Url_shortner_Application.Security.JwtUtil;
import com.shivaganesh.Url_shortner_Application.exception.DuplicateShortUrlException;
import com.shivaganesh.Url_shortner_Application.exception.ResourceNotFoundException;
import com.shivaganesh.Url_shortner_Application.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class urlServiceImpl implements urlService{

    final UrlRepository urlRepository;
    final UserRepository userRepository;
    private final AbstractHandlerMethodAdapter abstractHandlerMethodAdapter;

    @Override
    public Url createShortUrl(CreateUrlRequest request) {
        if(urlRepository.existsByShortUrl(request.getShortUrl())){
            throw new DuplicateShortUrlException("Url Already Exists");
        }

        Url url = Url.builder().shortUrl(request.getShortUrl())
                .orgUrl(request.getOrgUrl()).count(0).build();
        urlRepository.save(url);
        return url;
    }

    @Override
    public Url editShortUrl(CreateUrlRequest request) {
        String uid= JwtUtil.extractUid(request.getToken());
        if(!checkUser(uid,request.getShortUrl())){
            throw  new RuntimeException("Url Doesn't Belong to user");
        }
        Url url=this.getByShortUrl(request.getShortUrl());
        url.setOrgUrl(request.getOrgUrl());

        urlRepository.save(url);
        return url;
    }

    @Override
    public void deleteShortUrl(Map<String, String> request) {

        // 1️⃣ Extract UID from token
        String uid = JwtUtil.extractUid(request.get("token"));

        // 2️⃣ Fetch user ONCE
        User user = userRepository.findByUid(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // 3️⃣ Ownership check (authorization, not existence)
        if (user.getShortUrls() == null ||
                !user.getShortUrls().contains(request.get("shortUrl"))) {
            throw new UnauthorizedException("Url doesn't belong to user");
        }

        // 4️⃣ Ensure URL exists
        Url url = urlRepository.findByShortUrl(request.get("shortUrl"))
                .orElseThrow(() -> new ResourceNotFoundException("Url not found"));

        // 5️⃣ Remove reference FIRST
        user.getShortUrls().remove(request.get("shortUrl"));
        userRepository.save(user);

        // 6️⃣ Delete URL record
        urlRepository.delete(url);
    }


    @Override
    public boolean checkUser(String uid, String shortUrl) {
        User user= userRepository.findByUid(uid).orElseThrow(() ->new ResourceNotFoundException("User not Found"));
        return user.getShortUrls().contains(shortUrl);
    }

    @Override
    public List<urlResponse> getDashboardData(String uid) {

        User user = userRepository.findByUid(uid).orElseThrow(() ->new ResourceNotFoundException("User not Found"));

        return urlRepository.findByShortUrlIn(user.getShortUrls()).stream().map(url->new urlResponse(
                url.getOrgUrl(),
                url.getShortUrl(),
                url.getCount()
        )).toList();
    }

    @Override
    public Url getByShortUrl(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).orElseThrow(()-> new ResourceNotFoundException("Url not found")
        );

    }

    @Override
    public void incrementCount(Url url) {
        url.setCount(url.getCount()+1);
        urlRepository.save(url);
    }

    @Override
    public String handleToken(String token, String shorturl) {

        String uid;

        if(token==null || token.isBlank()){
            uid = UUID.randomUUID().toString();
        }
        else {
            try {
                uid= JwtUtil.extractUid(token);
            } catch (Exception e){
                uid = UUID.randomUUID().toString();
            }
        }

        String finalUid = uid;
        User user = userRepository.findByUid(uid).orElseGet(()-> User.builder().uid(finalUid).build());

        user.getShortUrls().add(shorturl);
        userRepository.save(user);
        return JwtUtil.generateToken(uid);
    }
}
