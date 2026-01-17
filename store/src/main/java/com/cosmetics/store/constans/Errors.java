package com.cosmetics.store.constans;

import lombok.Getter;

@Getter
public enum Errors {
    COD_801_NO_VALIDO("801","No existe certificado activo");

    private final String code;
    private final String message;

    //Enum Constructor
    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static class errors{
        public static final String COD_801_NO_VALIDO = "801";
    }
}
