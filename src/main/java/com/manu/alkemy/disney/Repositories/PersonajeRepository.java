package com.manu.alkemy.disney.Repositories;

import com.manu.alkemy.disney.Entities.Pelicula;
import com.manu.alkemy.disney.Entities.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    @Query("select p from Personaje p where p.nombre = :nombre")
    Personaje buscarPorNombre(String nombre);

    @Query("SELECT p FROM Personaje p WHERE TIMESTAMPDIFF(YEAR, p.edad, CURDATE()) = :edad")
    List<Personaje> filtrarPersonajesPorEdad(int edad);

    @Query("SELECT p, pe FROM Personaje p RIGHT JOIN p.peliculas pe WHERE pe.id = :id")
    List<Personaje> filtrarPersonajesPorPelicula(Long id);

}
