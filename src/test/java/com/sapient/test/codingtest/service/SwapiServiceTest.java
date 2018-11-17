package com.sapient.test.codingtest.service;


import com.sapient.test.codingtest.exception.InvalidTypeException;
import org.junit.Before;
import org.junit.Test;

public class SwapiServiceTest {

    private SwapiService service;

    @Test(expected = InvalidTypeException.class)
    public void shouldThrowExceptionWhenInvalidType() throws InvalidTypeException {
        service.search("dummy", "hello");
    }

    @Test
    public void shouldThrowServiceUnavailableException(){

    }

    @Test
    public void shouldCallSwapiExternalServiceForValidArguments(){

    }

    @Before
    public void setUp(){
        service = new SwapiService();
    }

}