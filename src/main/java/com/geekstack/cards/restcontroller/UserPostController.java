package com.geekstack.cards.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekstack.cards.model.Comment;
import com.geekstack.cards.model.UserPost;
import com.geekstack.cards.service.RabbitMQProducer;
import com.geekstack.cards.service.UserPostService;

@RestController
@RequestMapping("/api/userpost")
public class UserPostController {

    @Autowired
    private UserPostService userPostService;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @GetMapping
    public ResponseEntity<List<UserPost>> test(@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "20") String limit) {
        return ResponseEntity.ok(userPostService.listUserPost(Integer.parseInt(page),Integer.parseInt(limit)));
    }

    /**
     * Example of Json from form
     * {
     * "postType": "decklist",
     * "code": "ABC123",
     * "isTournamentDeck": true,
     * "timestamp": "2024-02-20",
     * "selectedCard": [
     * { "cardUid": "card_001", "imageSrc": "https://example.com/image1.jpg" }
     * ],
     * "listofcards": [
     * { "cardUid": "card_002", "imageSrc": "https://example.com/image2.jpg" }
     * ],
     * "listoflikes": [],
     * "listofcomments": [
     * 
     * ]
     * }
     * 
     * @param userId
     * @param userPost
     * @return
     */
    @PostMapping(path = { "/post/{userId}" }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Object>> userMakePost(@PathVariable String userId, @RequestBody UserPost userPost) {
        Map<String, Object> response = new HashMap<>();
        try {
            userPost.setUserId(userId);
            userPostService.createPost(userPost);
            response.put("message", "Post successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);        
        } catch (Exception e) {
            response.put("message", "Error creating post: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Map<String, Object>> userDeletePost(@PathVariable String postId) {
        Map<String, Object> response = new HashMap<>();
        try {
            userPostService.deletePost(postId);
            response.put("message", "Post deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", "Error deleting post: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/comment/{postId}/by/{userId}")
    public ResponseEntity<Map<String, Object>> commentPost(@PathVariable String postId,
            @PathVariable String userId,
            @RequestBody Comment comment) {
        Map<String, Object> response = new HashMap<>();
        try {
            String commentId = userPostService.commentPost(postId, comment.getComment(), userId);
            response.put("message", "Comment created successfully");
            response.put("commentId", commentId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", "Error adding deck: " + e.getMessage());
            response.put("comments", comment.getComment());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/comment/{postId}/delete/{commentId}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable String postId,
            @PathVariable String commentId) {
        Map<String, Object> response = new HashMap<>();
        try {
            userPostService.deleteCommmentFromPost(postId, commentId);
            response.put("message", "Comment deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", "Error deleting comments: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/like/{postId}/by/{userId}")
    public ResponseEntity<Map<String, Object>> likeAPost(@PathVariable String postId,
            @PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            System.out.println("QUEUEING:");
            rabbitMQProducer.sendLikeEvent(postId, userId);
            response.put("message", "like recorded successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", "Error liking post: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/unlike/{postId}/by/{userId}")
    public ResponseEntity<Map<String, Object>> unlikeAPost(@PathVariable String postId,
            @PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            userPostService.unlikePost(postId, userId);
            response.put("message", "unlike recorded successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", "Error unliking post: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
