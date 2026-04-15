package com.example.demo.repository;
import java.util.List;
import com.example.demo.module.Queue;   
import com.example.demo.module.User; 
import org.springframework.data.jpa.repository.JpaRepository;
public interface QueueRepository extends JpaRepository<Queue,Long>
{  
    List<Queue> findByCreatedBy(User user);// for advance @Query("SELECT q FROM Queue q WHERE q.createdBy = :user")
                                            //List<Queue> findQueuesByUser(@Param("user") User user);
}