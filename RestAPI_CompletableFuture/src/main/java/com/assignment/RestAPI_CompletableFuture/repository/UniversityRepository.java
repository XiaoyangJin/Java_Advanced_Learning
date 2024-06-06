package com.assignment.RestAPI_CompletableFuture.repository;

import com.assignment.RestAPI_CompletableFuture.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    // we don't need this if we don't want to store data into database for future use
}
