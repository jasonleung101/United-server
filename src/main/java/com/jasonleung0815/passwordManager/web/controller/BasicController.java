package com.jasonleung0815.passwordManager.web.controller;

import com.jasonleung0815.passwordManager.dao.ManagerRepository;
import com.jasonleung0815.passwordManager.model.Manager;
import com.jasonleung0815.passwordManager.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {

    @Autowired
    IManagerService managerService;

    @PostMapping("/add")
    public ResponseEntity<Manager> addItem(@RequestBody Manager addForm) throws Exception {
        Manager manager = managerService.addAccount(addForm);
        return ResponseEntity.ok().body(manager);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Manager> getItem(@PathVariable("id") Long id) throws Exception {
        Manager manager = managerService.getAccount(id);
        return ResponseEntity.ok().body(manager);
    }
}
