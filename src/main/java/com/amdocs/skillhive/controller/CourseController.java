package com.amdocs.skillhive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
  Course endpoints removed from the API surface.
  This controller returns 410 Gone for any call so clients know the resource is intentionally not available.
  If you prefer to delete the file entirely, remove it from the repo after ensuring no remaining references.
*/
@RestController
@RequestMapping("/api/courses")
@Deprecated
public class CourseController {

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
