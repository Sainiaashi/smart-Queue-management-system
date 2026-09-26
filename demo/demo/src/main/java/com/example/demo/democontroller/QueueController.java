package com.example.demo.democontroller;

import com.example.demo.module.Queue;
import com.example.demo.module.User;
import com.example.demo.repository.QueueRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createQueue(@RequestParam String name,
                                          @RequestParam String location,
                                          @RequestParam Long createdById) {

        User creator = userRepository.findById(createdById)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Queue queue = new Queue(name, location, creator);
        return ResponseEntity.ok(queueRepository.save(queue));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllQueues() {
        return ResponseEntity.ok(queueRepository.findAll());
    }
}