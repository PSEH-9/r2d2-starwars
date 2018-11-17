package com.sapient.test.codingtest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvalidTypeException extends Exception {

    private String type;

    @Override
    public String getMessage() {
        return type + " is a Invaild Type.";
    }
}
