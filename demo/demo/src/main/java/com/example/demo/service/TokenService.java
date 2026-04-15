package com.example.demo.service;

import com.example.demo.module.Token;
import com.example.demo.module.User;
import com.example.demo.module.Queue;

import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.QueueRepository;

import com.example.demo.enums.Status;
import com.example.demo.enums.Priority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenrepo;

    @Autowired
    private UserRepository userrepo;

    @Autowired
    private QueueRepository queuerepo;

    public Token bookToken(Long userId, Long queueId) {

        // 1. Fetch user
        User user = userrepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Fetch queue
        Queue queue = queuerepo.findById(queueId)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        // 3. Check duplicate
        List<Token> existingTokens =
                tokenrepo.findByUserAndQueueAndStatus(user, queue, Status.WAITING);

        if (!existingTokens.isEmpty()) {
            throw new RuntimeException("User already in queue");
        }

        // 4. Get last token
        Token lastToken =
                tokenrepo.findTopByQueueOrderByTokennumberDesc(queue);

        int nextToken;
        if (lastToken == null) {
            nextToken = 1;
        } else {
            nextToken = lastToken.getTokennumber() + 1;
        }

        // 5. Create token
        Token newToken = new Token();
        newToken.setUser(user);
        newToken.setQueue(queue);
        newToken.setStatus(Status.WAITING);
        newToken.setPriority(Priority.NORMAL);
        newToken.setTokennumber(nextToken);

        // 6. Save
        tokenrepo.save(newToken);

        return newToken;
    }
  public Token serveNextToken(Long queueid) {

    Queue queue = queuerepo.findById(queueid)
            .orElseThrow(() -> new RuntimeException("queue not found"));

    List<Token> tokens =
             tokenrepo.findByQueueAndStatusOrderByPriorityDescCreatedAtAsc(queue, Status.WAITING);

    if (tokens.isEmpty()) {
        throw new RuntimeException("no token to serve");
    }

    Token nexttoken = tokens.get(0);
    nexttoken.setStatus(Status.SERVED);

    tokenrepo.save(nexttoken);

    return nexttoken;
}
    public String getQueueStatus(Long userId, Long queueId) {

    // 1. Fetch user
    User user = userrepo.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // 2. Fetch queue
    Queue queue = queuerepo.findById(queueId)
            .orElseThrow(() -> new RuntimeException("Queue not found"));

    // 3. Get user's token
    List<Token> userTokens =
            tokenrepo.findByUserAndQueueAndStatus(user, queue, Status.WAITING);

    if (userTokens.isEmpty()) {
        throw new RuntimeException("User has no active token");
    }

    Token userToken = userTokens.get(0);

    // 4. Get all waiting tokens (FIFO)
    List<Token> tokens =
            tokenrepo.findByQueueAndStatusOrderByCreatedAtAsc(queue, Status.WAITING);

    // 5. Find position
    int position = -1;
    for (int i = 0; i < tokens.size(); i++) {
        if (tokens.get(i).getTokennumber() == userToken.getTokennumber()) {
            position = i + 1;
            break;
        }
    }

    // 6. Total waiting
    int totalWaiting = tokens.size();

    return "Your Token: " + userToken.getTokennumber() +
           ", Position: " + position +
           ", Total Waiting: " + totalWaiting;
}
}