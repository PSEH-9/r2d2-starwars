package com.sapient.test.codingtest.service;

import com.sapient.test.codingtest.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SwapiService {

    @Value("${swapi.service.url}")
    private String serviceUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Result search(String type, String name){
        Map rs = restTemplate.getForObject(serviceUrl + "/planets/?search=Alderaan", Map.class);
        Map results = (Map) (((List) rs.get("results")).get(0));

        return new Result(type,(int) rs.get("count"), name, (List<String>) results.get("films"));
    }

}
