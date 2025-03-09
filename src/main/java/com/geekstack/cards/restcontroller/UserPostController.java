package com.geekstack.cards.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<UserPost>> test() {
        return new ResponseEntity<List<UserPost>>(userPostService.listUserPost(), HttpStatus.OK);
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
    public ResponseEntity<String> userMakePost(@PathVariable String userId, @RequestBody UserPost userPost) {
        try {
            userPost.setUserId(userId);
            userPostService.createPost(userPost);
            return ResponseEntity.ok("Post successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding comment: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> userDeletePost(@PathVariable String postId) {
        try {
            userPostService.deletePost(postId);
            return ResponseEntity.ok("Post delete successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding comment: " + e.getMessage());
        }
    } 

    @PostMapping("/comment/{postId}/by/{userId}/")
    public ResponseEntity<String> commentPost(@PathVariable String postId,
            @PathVariable String userId,
            @RequestBody Comment comment) {
        try {
            String commentId = userPostService.commentPost(postId, comment.getComment(), userId);
            return ResponseEntity.ok("Comment added successfully:" + commentId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding comment: " + e.getMessage());
        }
    }

    @PostMapping("/comment/{postId}/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable String postId, @PathVariable String commentId){
        try {
            userPostService.deleteCommmentFromPost(postId,commentId);
            return ResponseEntity.ok("Comment deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding comment: " + e.getMessage());
        }
    }

    @PostMapping("/like/{postId}/by/{userId}")
    public ResponseEntity<String> likeAPost(@PathVariable String postId,
            @PathVariable String userId) {
        try {
            System.out.println("QUEUEING:");
            rabbitMQProducer.sendLikeEvent(postId, userId);
            return ResponseEntity.ok("like recorded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding comment: " + e.getMessage());
        }
    }

    @PostMapping("/unlike/{postId}/by/{userId}")
    public ResponseEntity<String> unlikeAPost(@PathVariable String postId,
            @PathVariable String userId) {
        try {
            userPostService.unlikePost(postId, userId);
            return ResponseEntity.ok("like unrecorded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding comment: " + e.getMessage());
        }
    }
    
}
