package com.assignment.RestAPI_CompletableFuture.repository;

import com.assignment.RestAPI_CompletableFuture.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Integer> {
}
