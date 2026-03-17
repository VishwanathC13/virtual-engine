package com.example.virtualengine.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/media")
public class MediaController {
    // Stub: generate signed URL for CDN (CloudFront) - replace with real signing logic
    @GetMapping("/signed-url")
    public ResponseEntity<?> signedUrl(@RequestParam String key) {
        String signed = String.format("https://cdn.example.com/%s?st=%d&sig=stub", key, Instant.now().getEpochSecond());
        return ResponseEntity.ok(Map.of("url", signed));
    }

    // Slido event ID endpoint
    @GetMapping("/slido")
    public ResponseEntity<?> slido() {
        return ResponseEntity.ok(Map.of("slidoEventId", "SLIDO_EVENT_ID_PLACEHOLDER"));
    }
}
