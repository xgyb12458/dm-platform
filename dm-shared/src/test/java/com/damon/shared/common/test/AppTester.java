package com.damon.shared.common.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AppTester {

    public static void main(String[] args) {
        SpringApplication.run(AppTester.class, args);
    }


    @GetMapping(path = "find")
    public ResponseEntity<String> find() {
        return ResponseEntity.ok("time out");
    }
}
