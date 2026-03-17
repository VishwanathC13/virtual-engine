package com.example.virtualengine.controller;

import com.example.virtualengine.analytics.TrackEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    @PostMapping("/track")
    @TrackEvent("user-action")
    public ResponseEntity<?> track(@RequestBody Map<String, Object> payload) {
        // Aspect will handle processing
        return ResponseEntity.ok(Map.of("status", "tracked"));
    }
}
