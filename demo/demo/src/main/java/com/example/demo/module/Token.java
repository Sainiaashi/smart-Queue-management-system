package com.example.demo.module;
import com.example.demo.enums.Status;
import com.example.demo.enums.Priority;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name="tokens")
public class Token
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int tokennumber;
    @Enumerated(EnumType.STRING)
    private Status status=Status.WAITING;
    @Enumerated(EnumType.STRING)
    private Priority priority=Priority.NORMAL;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="queue_id")
    private Queue queue;
    public Token(){}
    public Token(int tokennumber,Status status,Priority priority,User user,Queue queue)
    {
        this.tokennumber=tokennumber;
        this.status=status;
        this.priority=priority;
        this.user=user;
        this.queue=queue;
    }
    @PrePersist
public void onCreate() {
    this.createdAt = LocalDateTime.now();
}
    public Long getId(){return id;}
    public int getTokennumber(){return tokennumber;}
    public Status getStatus(){return status;}
    public Priority getPriority(){return priority;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public User getUser(){return user;}
    public Queue getQueue(){return queue;}
 
   public void setTokennumber(int tokennumber){this.tokennumber=tokennumber;}
   public void setStatus(Status status){this.status=status;}
    public void setPriority(Priority priority){this.priority=priority;}
    public void setUser(User user){this.user=user;}
    public void setQueue(Queue queue){this.queue=queue;}
}