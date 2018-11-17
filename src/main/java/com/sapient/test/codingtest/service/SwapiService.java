package com.sapient.test.codingtest.service;

import com.sapient.test.codingtest.exception.InvalidTypeException;
import com.sapient.test.codingtest.exception.ServiceUnavailableException;
import com.sapient.test.codingtest.model.Result;
import com.sapient.test.codingtest.model.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SwapiService {

    public static final String SEARCH_QUERY = "/{type}/?search={name}";

    @Value("${swapi.service.url}")
    private String serviceUrl;


    private RestTemplate restTemplate;

    @Autowired
    public SwapiService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Result search(String type, String name) throws InvalidTypeException, ServiceUnavailableException {

        checkType(type);

        return getData(type, name);
    }

    private Result getData(String type, String name) throws ServiceUnavailableException {
        Result result;
        try {
            Map rs = restTemplate.getForObject(serviceUrl + SEARCH_QUERY, Map.class, type, name);
            Map results = (Map) (((List) rs.get("results")).get(0));
            result = new Result(type, (int) rs.get("count"), name, (List<String>) results.get("films"));
        } catch (HttpClientErrorException e) {
            throw new ServiceUnavailableException(e.getCause());
        }
        return result;
    }

    private void checkType(String type) throws InvalidTypeException {
        try {
            Types validType = Types.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTypeException(type);
        }
    }

}
