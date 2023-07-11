package com.manu.alkemy.disney.Controller;

import com.manu.alkemy.disney.Entities.Genero;
import com.manu.alkemy.disney.Services.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genre")
public class GeneroController {

    @Autowired
    private GeneroServiceImpl service;

    @PostMapping
    public ResponseEntity<Genero> crearGenero(@RequestBody Genero genero){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(genero));
    }

}
