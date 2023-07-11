package com.manu.alkemy.disney.Services;

import com.manu.alkemy.disney.Entities.Dto.PersonajeDTO;
import com.manu.alkemy.disney.Entities.Personaje;
import com.manu.alkemy.disney.Exceptions.PeliculaNotFoundException;
import com.manu.alkemy.disney.Exceptions.PersonajeAlreadyExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PersonajeService {

    Personaje crearPersonaje(Personaje personaje) throws PeliculaNotFoundException, PersonajeAlreadyExistsException, PersonajeNotFoundException;
    List<Personaje> findAll();
    Optional<Personaje> findById(Long id) throws PersonajeNotFoundException;
    Personaje actualizarPersonaje(Personaje personaje, Long id) throws PersonajeNotFoundException;
    PersonajeDTO buscarPersonajePorNombre(String nombre) throws PersonajeNotFoundException;
    void delete(Long id) throws PersonajeNotFoundException;
    List<Personaje> filtrarPersonajesPorEdad(int edad);
    List<Personaje> filtrarPersonajesPorPelicula(Long id);


}
