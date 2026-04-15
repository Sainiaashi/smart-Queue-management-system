package com.example.demo.democontroller;
import com.example.demo.service.TokenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    // 1. Book Token
    @PostMapping("/book")
    public ResponseEntity<?> bookToken(@RequestParam Long userId,
                                       @RequestParam Long queueId) {

        return ResponseEntity.ok(tokenService.bookToken(userId, queueId));
    }

    // 2. Serve Next Token
    @PostMapping("/serve/{queueId}")
    public ResponseEntity<?> serveNextToken(@PathVariable Long queueId) {

        return ResponseEntity.ok(tokenService.serveNextToken(queueId));
    }

    // 3. Get Queue Status
    @GetMapping("/status")
    public ResponseEntity<?> getQueueStatus(@RequestParam Long userId,
                                            @RequestParam Long queueId) {

        return ResponseEntity.ok(tokenService.getQueueStatus(userId, queueId));
    }
}