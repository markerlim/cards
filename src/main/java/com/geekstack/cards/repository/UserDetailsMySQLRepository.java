package com.geekstack.cards.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsMySQLRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SQL_USER_EXIST = """
            SELECT * FROM users WHERE userId = ?
            """;
    private final static String SQL_SAVE_USER = """
            INSERT INTO users(userId, name, displaypic) VALUES (?,?,?)
            """;
    
    public int createUser(String userId, String name, String image){
        int count = jdbcTemplate.update(SQL_SAVE_USER,userId,name,image);
        return count;
    }

    public boolean userExist(String userId){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_USER_EXIST, userId);
        if(rs.next()){
            return true;
        }
        return false;
    }
}
