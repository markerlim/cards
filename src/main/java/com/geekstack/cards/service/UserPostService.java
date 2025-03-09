package com.geekstack.cards.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.UserPost;
import com.geekstack.cards.repository.UserPostMongoRepository;

@Service
public class UserPostService {

    @Autowired
    private UserPostMongoRepository userPostMongoRepository;

    // Get a list of user post with details
    public List<UserPost> listUserPost() {
        List<UserPost> loup = userPostMongoRepository.userPostingsDefault();
        return loup;
    }

    // Create a post
    public void createPost(UserPost userPost) {
        userPostMongoRepository.userPostAction(userPost);
    }

    // Delete a post
    public void deletePost(String postId) {
        userPostMongoRepository.deletePost(postId);
    }

    // Comment on a post by postId
    public String commentPost(String postId, String comment, String userId) {
        return userPostMongoRepository.addComment(postId, comment, userId);
    }

    // Delete comment from a post where comment is from and delete by commentId
    public void deleteCommmentFromPost(String postId, String commentId) {
        userPostMongoRepository.deleteComment(postId, commentId);
    }

    // Unlike a post
    public void unlikePost(String postId, String userId) {

        userPostMongoRepository.unlikeAPost(postId, userId);

    }

}
