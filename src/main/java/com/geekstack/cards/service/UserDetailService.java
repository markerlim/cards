package com.geekstack.cards.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geekstack.cards.repository.UserDetailsMongoRepository;
import com.geekstack.cards.repository.UserDetailsMySQLRepository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailsMongoRepository userDetailsMongoRepository;

    @Autowired
    private UserDetailsMySQLRepository userDetailsMySQLRepository;

    @Transactional
    public int createUser(String payload) {
        try {
            JsonReader reader = Json.createReader(new StringReader(payload));
            JsonObject jObj = reader.readObject();
            String userId = jObj.getString("uid");
            String name = jObj.getString("displayName");
            String displaypic = jObj.getString("photoURL");
            String email = jObj.getString("email");
            if (!userDetailsMySQLRepository.userExists(userId)) {
                userDetailsMySQLRepository.createUser(userId, name, displaypic, email);
                userDetailsMongoRepository.createUser(userId);
                return 1;
            }
            return 0;

        } catch (Exception e) {

            return 2;

        }

    }

}
