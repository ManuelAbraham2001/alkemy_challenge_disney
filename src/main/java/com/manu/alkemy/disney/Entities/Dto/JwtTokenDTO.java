package com.manu.alkemy.disney.Entities.Dto;

import lombok.Data;

@Data
public class JwtTokenDTO {

    private String tokenType = "Bearer ";

    private String token;

    public JwtTokenDTO(String token) {
        this.token = token;
    }

}
