package com.geekstack.cards.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseService.class);

    @Autowired
    private FirebaseAuth firebaseAuth;

    public FirebaseToken verifyIdToken(String idToken) throws Exception {
        logger.info("ID Token received: {}", idToken);        
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return decodedToken;
        } catch (Exception e) {
            logger.error("Error verifying ID token", e);
            throw new Exception("Invalid ID token", e);
        }
    }
}
