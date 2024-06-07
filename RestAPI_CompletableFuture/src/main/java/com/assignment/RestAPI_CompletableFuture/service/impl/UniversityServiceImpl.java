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
import java.util.Arrays;
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
    public List<University> getAllUniversitiesByCountries(List<String> countries){
        List<CompletableFuture<University[]>> futures = new ArrayList<>();
        for (String country : countries) {
            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> restTemplate.getForObject(url + "?country=" + country, University[].class), executorService));
        }

        List<University> res = new ArrayList<>();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenAccept(Ignored -> futures.forEach(
                        cf -> {
                            University[] uni = cf.join();
                            res.addAll(Arrays.asList(uni));
                        }
                )).join();
        return res;
    }
}
