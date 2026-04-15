package com.example.demo.repository;
import java.util.List;
import com.example.demo.module.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}