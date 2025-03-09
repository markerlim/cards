package com.geekstack.cards.model;

import java.time.LocalDateTime;


public class Comment {
    
    private String commentId;
    private String comment;
    private String userId;
    private LocalDateTime timestamp;

    public Comment() {

    }

    public Comment(String commentId, String comment, String userId, LocalDateTime timestamp) {
        this.commentId = commentId;
        this.comment = comment;
        this.userId = userId;
        this.timestamp = timestamp;
    }
    

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
