package com.example.demo.module;
import com.example.demo.enums.Role;
import java.util.*;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name="users")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique=true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<Token>tokens=new ArrayList<>();//many user many ->token

    @OneToMany(mappedBy="createdBy", cascade=CascadeType.ALL)
private List<Queue> queues;// many admin ->many queue

    public User(){}
    public User(String name,String email,String password,Role role)
    {
        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }
    public Role getRole()
    {
        return role;
    }
    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public void setRole(Role role)
    {
        this.role=role;
    }
}