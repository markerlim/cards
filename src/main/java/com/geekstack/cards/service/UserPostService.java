package com.geekstack.cards.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.Comment;
import com.geekstack.cards.model.UserPost;
import com.geekstack.cards.repository.UserPostMongoRepository;
import com.geekstack.cards.repository.UserPostMySQLRepository;

@Service
public class UserPostService {

    @Autowired
    private UserPostMongoRepository userPostMongoRepository;

    @Autowired
    private UserPostMySQLRepository userPostMySQLRepository;

    public List<UserPost> listUserPost(int page, int limit) {
        List<UserPost> userPosts = userPostMongoRepository.userPostingsDefault(page,limit);

        Set<String> uniquePostUserIds = userPosts.stream()
                .map(UserPost::getUserId)
                .collect(Collectors.toSet());

        Set<String> uniqueCommentUserIds = new HashSet<>();
        for (UserPost post : userPosts) {
            List<Comment> comments = post.getListofcomments();
            if (comments != null) {
                comments.stream()
                        .map(Comment::getUserId)
                        .filter(Objects::nonNull)
                        .forEach(uniqueCommentUserIds::add);
            }
        }

        Set<String> allUniqueUserIds = new HashSet<>();
        allUniqueUserIds.addAll(uniquePostUserIds);
        allUniqueUserIds.addAll(uniqueCommentUserIds);

        List<Map<String, Object>> allUserDetails = userPostMySQLRepository
                .batchGetUser(new ArrayList<>(allUniqueUserIds));
        System.out.println("finish user details pull\n");

        Map<String, Map<String, Object>> userMap = new HashMap<>();
        for (Map<String, Object> user : allUserDetails) {
            String userId = (String) user.get("userId");
            userMap.put(userId, user);
        }

        for (UserPost post : userPosts) {
            String postUserId = post.getUserId();
            Map<String, Object> postUser = userMap.get(postUserId);

            if (postUser != null) {
                post.setDisplaypic(postUser.get("displaypic") != null ? postUser.get("displaypic").toString() : null);
                post.setName(postUser.get("name") != null ? postUser.get("name").toString() : null);
            }

            List<Comment> comments = post.getListofcomments();
            if (comments != null) {
                for (Comment comment : comments) {
                    String commentUserId = comment.getUserId();
                    if (commentUserId != null) {
                        Map<String, Object> commentUser = userMap.get(commentUserId);
                        if (commentUser != null) {
                            comment.setDisplaypic(
                                    commentUser.get("displaypic") != null ? commentUser.get("displaypic").toString()
                                            : null);
                            comment.setName(
                                    commentUser.get("name") != null ? commentUser.get("name").toString() : null);
                        }
                    }
                }
            }
        }

        return userPosts;
    }

    // This method is no longer needed as we're handling all users in one go
    // You can remove this method entirely
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
