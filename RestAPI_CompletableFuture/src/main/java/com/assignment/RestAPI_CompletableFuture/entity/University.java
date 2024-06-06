package com.assignment.RestAPI_CompletableFuture.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class University {
    private String name;
    private List<String> domains;
    private List<String> web_pages;
}
