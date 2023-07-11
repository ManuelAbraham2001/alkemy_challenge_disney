package com.manu.alkemy.disney.Exceptions;

import java.util.HashMap;
import java.util.Map;

public class PeliculaNotFoundException extends Exception{

    private String causa;
    public PeliculaNotFoundException(String message, String causa) {
        super(message);
        this.causa = causa;
    }

    public Map<String, Object> getError(){
        Map<String, Object> error = new HashMap<>();
        error.put("Error", getMessage());
        error.put("Causa", causa);
        return error;
    }
}
