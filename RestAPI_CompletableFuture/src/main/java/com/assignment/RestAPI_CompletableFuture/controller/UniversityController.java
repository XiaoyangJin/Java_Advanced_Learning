package com.assignment.RestAPI_CompletableFuture.controller;

import com.assignment.RestAPI_CompletableFuture.entity.University;
import com.assignment.RestAPI_CompletableFuture.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/university")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUniversities(){
        return new ResponseEntity<>(universityService.getAllUniversities(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getUniversities(@RequestParam(required = false) List<String> countries){
        if (countries == null || countries.isEmpty()){
            List<University> universities = universityService.getAllUniversities();
            return ResponseEntity.ok(universities);
        } else {
            CompletableFuture<List<University>> universitiesFuture = universityService.getAllUniversitiesByCountries(countries);
            return universitiesFuture.thenApply(ResponseEntity::ok).join();
        }
    }
}
