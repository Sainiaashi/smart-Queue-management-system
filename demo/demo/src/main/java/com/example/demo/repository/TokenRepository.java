package com.example.demo.repository;
import java.util.List;

import com.example.demo.module.Token;   
import com.example.demo.module.Queue;   
import com.example.demo.module.User;     
import com.example.demo.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TokenRepository extends JpaRepository<Token,Long>
{
    List<Token> findByQueue(Queue queue);
    List<Token> findByUser(User user);
    List<Token> findByQueueAndStatus(Queue queue,Status status);
    List<Token> findByQueueAndStatusOrderByCreatedAtAsc(Queue queue,Status status);
    Token findTopByQueueOrderByTokennumberDesc(Queue queue);
    List<Token> findByUserAndQueueAndStatus(User user,Queue queue,Status status);
    List<Token> findByQueueAndStatusOrderByPriorityDescCreatedAtAsc(Queue queue,Status status);
}