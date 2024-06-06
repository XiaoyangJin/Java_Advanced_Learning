package com.assignment.RestAPI_CompletableFuture.service.impl;

import com.assignment.RestAPI_CompletableFuture.entity.University;
import com.assignment.RestAPI_CompletableFuture.exceptions.CustomException;
import com.assignment.RestAPI_CompletableFuture.service.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UniversityServiceImpl implements UniversityService {
    private final RestTemplate restTemplate;

    public UniversityServiceImpl(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<University> getAllUniversities() {
        try{
            String url = "http://universities.hipolabs.com/search";
            University[] universities = restTemplate.getForObject(url, University[].class);
            if(universities == null){
                throw new CustomException("Found Nothing");
            }
            return List.of(universities);
        } catch(Exception e){
            log.error("Error fetching all universities from first api(getAllUniversities): " + e.getMessage());
            throw new CustomException("Error fetching all universities from first api(getAllUniversities): " + e.getMessage());
        }
    }
}
