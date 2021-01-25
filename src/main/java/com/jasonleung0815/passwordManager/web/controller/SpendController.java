package com.jasonleung0815.passwordManager.web.controller;

import com.jasonleung0815.passwordManager.model.Manager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spending")
public class SpendController {

    @GetMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok().build();
    }
}
