package com.geekstack.cards.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.geekstack.cards.repository.UserPostMongoRepository;

@Service
@RabbitListener(queues = "likeQueue")
public class RabbitMQConsumer {

    @Autowired
    private UserPostMongoRepository userPostMongoRepository;

    private final List<String> likeBuffer = new ArrayList<>();


    @RabbitHandler
    public void receiveLike(String message) {
        synchronized (likeBuffer) {
            likeBuffer.add(message);
        }
    }

    @Scheduled(fixedRate = 5000)
    public void processLikes() {
        synchronized (likeBuffer) {
            if (!likeBuffer.isEmpty()) {
                System.out.println("Processing " + likeBuffer.size() + " likes in batch...");
                bulkInsertIntoDatabase(likeBuffer);
                likeBuffer.clear();
            }
        }
    }

    private void bulkInsertIntoDatabase(List<String> likes) {
        Map<String, List<String>> holder = new HashMap<>();

        for (String entry : likes) {

            String[] array = entry.split(":");
            String userId = array[1];
            List<String> list = holder.computeIfAbsent(userId, k -> new ArrayList<>());
            list.add(array[0]);
            holder.put(userId, list);
        }

        for (String userId : holder.keySet()) {
            userPostMongoRepository.likeMultiplePosts(holder.get(userId), userId);
        }

        System.out.println("Batch writing " + likes.size() + " likes to database.");
    }

}
