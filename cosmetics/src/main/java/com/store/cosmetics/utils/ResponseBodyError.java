package com.store.cosmetics.utils;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
public class ResponseBodyError { //implements Serializable
//    @Serial
//    private static final long serialVersionUID = 1L;
    private String code;
    private Object message;

    public ResponseBodyError(String code, Object message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBodyError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
