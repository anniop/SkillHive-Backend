package com.amdocs.skillhive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@Deprecated
public class RoleController {

    // Roles are managed in the DB/admin UI only; API endpoints removed.

    @GetMapping
    public ResponseEntity<Void> list() {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> get(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody(required = false) Object payload) {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody(required = false) Object payload) {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }
}

