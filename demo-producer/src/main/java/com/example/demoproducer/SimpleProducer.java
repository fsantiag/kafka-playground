package com.example.demoproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SimpleProducer {

    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public SimpleProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    public ResponseEntity sendMessage(@RequestParam(value="msg", defaultValue="foo") String msg) {
        kafkaTemplate.send("test-sleuth", UUID.randomUUID().toString(), msg);
        return ResponseEntity.ok("Done!");
    }
}
