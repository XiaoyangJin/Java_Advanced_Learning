package com.assignment.RestAPI_CompletableFuture.service;

import com.assignment.RestAPI_CompletableFuture.entity.University;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface UniversityService {
    //1st api: search by name
    University[] getAllUniversities();
    //2nd api: accept list of countries and send request using multithreading
    CompletableFuture<List<University>> getAllUniversitiesByCountries(List<String> countries);
}
