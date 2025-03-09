package com.geekstack.cards.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.Notification;

@Repository
public class NotificationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SQL_SAVE_NOTIFICATION = """
            INSERT INTO notifications(receiverId,senderId, message, isRead, timestamp, linkId,type) into (?,?,?,?,?,?,?)
            """;
    
    private final static String SQL_GET_NOTIFICATION = """
            SELECT * FROM notifications WHERE userId = ? LIMIT = 5;
            """;
    
    public void save(Notification notification) {
        jdbcTemplate.update(SQL_SAVE_NOTIFICATION
        ,notification.getReceiverId()
        ,notification.getSenderId()
        ,notification.getMessage()
        ,notification.getIsRead()
        ,notification.getTimestamp()
        ,notification.getLinkId()
        ,notification.getType());
    }

    public List<Notification> getNoti(String userId){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_NOTIFICATION,userId);
        List<Notification> lon = new ArrayList<>();
        while(rs.next()){
            Notification n = new Notification();
            n.setReceiverId(userId);
            n.setSenderId(rs.getString("senderId"));
            n.setMessage(rs.getString("message"));
            n.setIsRead(rs.getBoolean("isRead"));
            n.setTimestamp(LocalDate.parse(rs.getString("timestamp")));
            n.setLinkId(rs.getString("linkId"));
            n.setType(rs.getString("type"));
            lon.add(n);
        }

        return lon;
    }
}
