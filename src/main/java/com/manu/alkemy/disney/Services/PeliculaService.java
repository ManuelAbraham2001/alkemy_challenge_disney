package com.manu.alkemy.disney.Services;

import com.manu.alkemy.disney.Entities.Pelicula;
import com.manu.alkemy.disney.Exceptions.PeliculaNotFoundException;
import com.manu.alkemy.disney.Exceptions.PersonajeAlreadyExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNoExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNotFoundException;

import java.util.List;

public interface PeliculaService {

    Pelicula crearPelicula(Pelicula pelicula) throws PersonajeNotFoundException;
    Pelicula actualizarPelicula(Pelicula pelicula, Long id) throws PeliculaNotFoundException;
    Pelicula buscarPeliculaPorNombre(String nombre) throws PeliculaNotFoundException;
    List<Pelicula> buscarPeliculaPorGenero(String genero) throws PeliculaNotFoundException;
    List<Pelicula> ordenarPeliculasASC();
    List<Pelicula> ordenarPeliculasDESC();
    void eliminarPelicula(Long id) throws PeliculaNotFoundException;
    void agregarOEliminarPersonajePelicula(Long idPelicula, Long idPersonaje, String metodo) throws PersonajeNotFoundException, PeliculaNotFoundException, PersonajeAlreadyExistsException, PersonajeNoExistsException;
}
