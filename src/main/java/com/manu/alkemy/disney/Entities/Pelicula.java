package com.manu.alkemy.disney.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    private int calificacion;
    @ManyToMany
    @JoinTable(name = "pelicula_generos",
            joinColumns = @JoinColumn(name = "pelicula_Id"),
            inverseJoinColumns = @JoinColumn(name = "generos_id"))
    private List<Genero> generos;

    @ManyToMany
    @JoinTable(name = "personajes_peliculas",
            joinColumns = @JoinColumn(name = "pelicula_Id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    @JsonIgnoreProperties({"peliculas", "personajes"})
    private List<Personaje> personajes;

    public Pelicula(){}

    public Pelicula(Long id, String titulo, LocalDate fechaCreacion, int calificacion, List<Genero> generos) {
        this.id = id;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.generos = generos;
    }

    public Pelicula(Long id, String titulo, LocalDate fechaCreacion, int calificacion, List<Genero> generos, List<Personaje> personajes) {
        this.id = id;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.generos = generos;
        this.personajes = personajes;
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

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }


}
