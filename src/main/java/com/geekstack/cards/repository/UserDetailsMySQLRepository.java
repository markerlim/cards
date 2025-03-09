package com.geekstack.cards.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsMySQLRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SQL_USER_EXIST = """
            SELECT COUNT(*) FROM users WHERE userId = ?
            """;
    private final static String SQL_SAVE_USER = """
            INSERT INTO users(userId, name, displaypic, email) VALUES (?,?,?,?)
            """;
    
    public int createUser(String userId, String name, String image, String email){
        int count = jdbcTemplate.update(SQL_SAVE_USER,userId,name,image,email);
        return count;
    }

    public boolean userExists(String userId) {
        Integer count = jdbcTemplate.queryForObject(SQL_USER_EXIST, Integer.class, userId);
        return count != null && count > 0;
    }
    
}
