package com.sapient.test.codingtest.controller;

import com.sapient.test.codingtest.model.Result;
import com.sapient.test.codingtest.service.SwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataSearchController {

    @Autowired
    private SwapiService service;

    @GetMapping("/{type}/{name}")
    public Result search(@PathVariable("type") String type, @PathVariable("name") String name){
        return service.search(type, name);
    }

}
