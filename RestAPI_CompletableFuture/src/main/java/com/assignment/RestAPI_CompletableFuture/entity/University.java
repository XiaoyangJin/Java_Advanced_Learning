package com.assignment.RestAPI_CompletableFuture.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class University {

    @Id
    private int university_id;

    private String name;
    private String domain;
    private String web_pages;
}
