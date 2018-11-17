package com.sapient.test.codingtest.e2e;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApp {

    @LocalServerPort
    private int port;

    TestRestTemplate template = new TestRestTemplate();


    @Test
    public void testPositiveFlow(){
        String result = template.getForObject(createURLWithPort("/api/{type}/{name}"), String.class, "people", "C-3PO");
        Assert.assertEquals(expected, result);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private String expected = "{\"type\":\"people\",\"count\":1,\"name\":\"C-3PO\",\"films\":[\"https://swapi.co/api/films/2/\",\"https://swapi.co/api/films/5/\",\"https://swapi.co/api/films/4/\",\"https://swapi.co/api/films/6/\",\"https://swapi.co/api/films/3/\",\"https://swapi.co/api/films/1/\"]}";
}
