package com.assignment.RestAPI_CompletableFuture.service.impl;

import com.assignment.RestAPI_CompletableFuture.entity.University;
import com.assignment.RestAPI_CompletableFuture.exceptions.CustomException;
import com.assignment.RestAPI_CompletableFuture.service.UniversityService;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class UniversityServiceImpl implements UniversityService {
    private final RestTemplate restTemplate;
    private final ExecutorService executorService;

    @Value("${university-url}")
    private String url;

    @Autowired
    public UniversityServiceImpl(@Qualifier("restTemplate") RestTemplate restTemplate, ExecutorService executorService) {
        this.restTemplate = restTemplate;
        this.executorService = executorService;
    }

    @Override
    public University[] getAllUniversities() {
        return restTemplate.getForObject(url, University[].class);
    }

    @Override
    public CompletableFuture<List<University>> getAllUniversitiesByCountries(List<String> countries){
        List<CompletableFuture<List<University>>> futures = new ArrayList<>();
        for (String country : countries) {
            futures.add(CompletableFuture.supplyAsync(() -> {
                University[] universities = restTemplate.getForObject(url + "?country=" + country.replace(" ", "+"), University[].class);
                if (universities == null) return new ArrayList<University>();
                return List.of(universities);
            }, executorService));
        }

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(ignored -> {
                    List<University> res = new ArrayList<>();
                    futures.forEach(future -> res.addAll(future.join()));
                    return res;
                });
    }
}
