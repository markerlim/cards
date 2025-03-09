package com.geekstack.cards.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue likesQueue;
    
    @Autowired
    private Queue unlikesQueue;

    public void sendLikeEvent(String postId, String userId) {
        String message = postId + ":" + userId;
        this.rabbitTemplate.convertAndSend(likesQueue.getName(), message);
        System.out.printf("Queued like event: %s%n", message);
    }

    public void sendUnlikeEvent(String postId, String userId) {
        String message = postId + ":" + userId;
        this.rabbitTemplate.convertAndSend(unlikesQueue.getName(), message);
        System.out.printf("Queued unlike event: %s%n", message);
    }
}

