package com.geekstack.cards.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geekstack.cards.repository.UserDetailsMongoRepository;
import com.geekstack.cards.repository.UserDetailsMySQLRepository;

@Service
public class UserDetailService {

    @Autowired
    private UserDetailsMongoRepository userDetailsMongoRepository;

    @Autowired
    private UserDetailsMySQLRepository userDetailsMySQLRepository;

    @Transactional
    public int createUser(String userId, String name, String displaypic, String email) {
        try {
            if (userDetailsMySQLRepository.userExists(userId).isEmpty()) {
                userDetailsMySQLRepository.createUser(userId, name, displaypic, email);
                userDetailsMongoRepository.createUser(userId);
                return 1;
            }
            return 0;

        } catch (Exception e) {

            return 2;

        }
    }

    public Map<String,Object> getOneUser(String userId){
        Map<String,Object> holder = new HashMap<>();
        holder.put("gsMongoUser",userDetailsMongoRepository.getOneUser(userId));
        holder.put("gsSQLUser",userDetailsMySQLRepository.userExists(userId));
        return holder;
    }
}
