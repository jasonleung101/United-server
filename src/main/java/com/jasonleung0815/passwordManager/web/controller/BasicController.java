package com.jasonleung0815.passwordManager.web.controller;

import com.jasonleung0815.passwordManager.dao.ManagerRepository;
import com.jasonleung0815.passwordManager.model.Manager;
import com.jasonleung0815.passwordManager.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BasicController {

    @Autowired
    IManagerService managerService;

    @Autowired
    ManagerRepository managerRepository;

    @PostMapping("/add")
    public ResponseEntity<Manager> addItem(@RequestBody Manager addForm) throws Exception {
        Manager manager = managerService.addAccount(addForm);
        return ResponseEntity.ok().body(manager);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Manager>> getAllItems() {
        List<Manager> manager = managerRepository.findAll();
        return ResponseEntity.ok().body(manager);
    }

    @GetMapping("/get")
    public ResponseEntity<Manager> getItem(@RequestParam() Long id) throws Exception {
        Manager manager = managerService.getAccount(id);
        if (manager == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(manager);
    }

    @DeleteMapping("/del")
    public ResponseEntity<Manager> delItem(@RequestParam Long id) {
        Manager manager = managerService.delAccount(id);
        if (manager == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(manager);
    }
}
