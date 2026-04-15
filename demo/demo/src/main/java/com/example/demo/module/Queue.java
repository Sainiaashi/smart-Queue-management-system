package com.example.demo.module;
import java.util.*;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name="queue")
public class Queue
{
    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;
     private String name;
     private String location;
     @ManyToOne
     @JoinColumn(name="created_by")
     private User createdBy;

    @OneToMany(mappedBy="queue", cascade=CascadeType.ALL)
    private List<Token> tokens = new ArrayList<>();

     public Queue(){}
     public Queue(String name,String location,User createdBy)
     {
        this.name=name;
        this.location=location;
        this.createdBy=createdBy;
     }
     public String getName()
     {
        return name;
     }
     public String getLocation()
     {
        return location;
     }
     public User getCreatedBy()
     {
        return createdBy;
     }
     public Long getID(){return id;}
     public void setName(String name){this.name=name;}
     public void setLocation(String location){this.location=location;}
     public void setcreaatedBy(User createdBy){this.createdBy=createdBy;}
}