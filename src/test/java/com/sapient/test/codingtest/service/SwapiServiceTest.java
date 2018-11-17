package com.sapient.test.codingtest.service;


import com.sapient.test.codingtest.exception.InvalidTypeException;
import com.sapient.test.codingtest.exception.ServiceUnavailableException;
import com.sapient.test.codingtest.model.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SwapiServiceTest {

    private SwapiService service;

    private RestTemplate restTemplate;

    @Test(expected = InvalidTypeException.class)
    public void shouldThrowExceptionWhenInvalidType() throws InvalidTypeException, ServiceUnavailableException {
        service.search("dummy", "hello");
    }

    @Test(expected = ServiceUnavailableException.class)
    public void shouldThrowServiceUnavailableException() throws ServiceUnavailableException, InvalidTypeException {
        when(restTemplate.getForObject(anyString(), any(), anyString(), anyString())).thenThrow(HttpClientErrorException.class);
        service.search("vehicles", "ss");
    }

    @Test
    public void shouldCallSwapiExternalServiceForValidArguments() throws ServiceUnavailableException, InvalidTypeException {
        Map responseMap = new HashMap();
        responseMap.put("count", 0);

        List results = new ArrayList();
        Map data = new HashMap();
        data.put("films", new ArrayList<>());

        results.add(data);
        responseMap.put("results", results);

        when(restTemplate.getForObject(anyString(), any(), anyString(), anyString())).thenReturn(responseMap);

        Result actual = service.search("vehicles", "sample");

        verify(restTemplate, times(1)).getForObject(anyString(), any(), anyString(), anyString());
    }

    @Before
    public void setUp(){
        restTemplate = mock(RestTemplate.class);
        service = new SwapiService(restTemplate);
    }

}