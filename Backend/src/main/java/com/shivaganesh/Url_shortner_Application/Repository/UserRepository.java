package com.shivaganesh.Url_shortner_Application.Repository;

import com.shivaganesh.Url_shortner_Application.Model.Url;
import com.shivaganesh.Url_shortner_Application.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.rmi.server.UID;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUid(String uid);

    User getUsersById(String id);
}
