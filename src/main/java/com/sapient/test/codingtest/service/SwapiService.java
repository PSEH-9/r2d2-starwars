package com.sapient.test.codingtest.service;

import com.sapient.test.codingtest.exception.InvalidTypeException;
import com.sapient.test.codingtest.model.Result;
import com.sapient.test.codingtest.model.Types;
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

    public Result search(String type, String name) throws InvalidTypeException {

        try{
            Types validType = Types.valueOf(type.toUpperCase());
        }catch (IllegalArgumentException e){
            throw new InvalidTypeException(type);
        }

        Map rs = restTemplate.getForObject(serviceUrl + "/{type}/?search={name}", Map.class, type, name);
        Map results = (Map) (((List) rs.get("results")).get(0));

        return new Result(type,(int) rs.get("count"), name, (List<String>) results.get("films"));
    }

}
