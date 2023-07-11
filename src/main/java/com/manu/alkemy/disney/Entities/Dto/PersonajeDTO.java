package com.manu.alkemy.disney.Entities.Dto;

import com.manu.alkemy.disney.Entities.Pelicula;

import java.util.List;

public class PersonajeDTO {

    private Long id;
    private String nombre;
    private int edad;
    private int peso;
    private String historia;

    public PersonajeDTO(){}

    private List<PeliculaDTO> peliculas;
    public PersonajeDTO(Long id, String nombre, int edad, int peso, String historia, List<PeliculaDTO> peliculas) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.peliculas = peliculas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public List<PeliculaDTO> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<PeliculaDTO> peliculas) {
        this.peliculas = peliculas;
    }
}
