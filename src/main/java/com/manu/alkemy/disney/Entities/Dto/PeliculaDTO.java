package com.manu.alkemy.disney.Entities.Dto;

import com.manu.alkemy.disney.Entities.Genero;
import jakarta.persistence.Column;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class PeliculaDTO {
    private Long id;
    private String titulo;
    private LocalDate fechaCreacion;
    private int calificacion;
    private List<Genero> generos;

    public PeliculaDTO(Long id, String titulo, LocalDate fechaCreacion, int calificacion, List<Genero> generos) {
        this.id = id;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.generos = generos;
    }

    public PeliculaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
