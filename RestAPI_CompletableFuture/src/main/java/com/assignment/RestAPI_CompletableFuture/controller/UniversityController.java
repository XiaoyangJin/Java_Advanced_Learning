package com.assignment.RestAPI_CompletableFuture.controller;

import com.assignment.RestAPI_CompletableFuture.entity.University;
import com.assignment.RestAPI_CompletableFuture.service.UniversityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/university")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities() {
        List<University> universities = universityService.getAllUniversities();
        return ResponseEntity.ok(universities);
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<List<University>>> getAllUniversitiesByCountries(@RequestBody List<String> countries){
        return universityService.getAllUniversitiesByCountries(countries)
                .thenApply(ResponseEntity::ok);
    }
}
