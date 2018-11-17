package com.sapient.test.codingtest.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceUnavailableException extends Exception {
    public ServiceUnavailableException(Throwable cause) {
        super(cause);
    }
}
