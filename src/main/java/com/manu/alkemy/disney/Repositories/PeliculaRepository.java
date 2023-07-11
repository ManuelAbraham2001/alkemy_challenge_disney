package com.manu.alkemy.disney.Repositories;

import com.manu.alkemy.disney.Entities.Pelicula;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    @Query("select p from Pelicula p where p.titulo = :nombre")
    Pelicula buscarPeliculaPorNombre(String nombre);

    @Query("SELECT p, g FROM Pelicula p RIGHT JOIN p.generos g WHERE g.nombre = :genero")
    List<Pelicula> buscarPeliculaPorGenero(String genero);

    @Query("SELECT p FROM Pelicula p ORDER BY p.titulo ASC")
    List<Pelicula> ordenarPeliculasASC();

    @Query("SELECT p FROM Pelicula p ORDER BY p.titulo DESC")
    List<Pelicula> ordenarPeliculasDESC();
}
