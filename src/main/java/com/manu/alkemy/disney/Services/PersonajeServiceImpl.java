package com.manu.alkemy.disney.Services;

import com.manu.alkemy.disney.Entities.Dto.PeliculaDTO;
import com.manu.alkemy.disney.Entities.Dto.PersonajeDTO;
import com.manu.alkemy.disney.Entities.Pelicula;
import com.manu.alkemy.disney.Entities.Personaje;
import com.manu.alkemy.disney.Exceptions.PeliculaNotFoundException;
import com.manu.alkemy.disney.Exceptions.PersonajeAlreadyExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNotFoundException;
import com.manu.alkemy.disney.Repositories.PeliculaRepository;
import com.manu.alkemy.disney.Repositories.PersonajeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService{

    @Autowired
    PersonajeRepository repo;
    @Autowired
    PeliculaRepository repoPeliculas;

    @Override
    public Personaje crearPersonaje(Personaje personaje) throws PeliculaNotFoundException, PersonajeAlreadyExistsException, PersonajeNotFoundException {

        if(repo.buscarPorNombre(personaje.getNombre()) != null){
            throw new PersonajeAlreadyExistsException("Error al crear el personaje", "El personaje " + personaje.getNombre() + " ya existe");
        }

        List<Pelicula> peliculas = new ArrayList<>();

        for (Pelicula p : personaje.getPeliculas()) {
            Pelicula pelicula = repoPeliculas.findById(p.getId()).orElseThrow(() -> new PeliculaNotFoundException("La pelicula no existe", "No existe la pelicula con el id " + p.getId()));
            pelicula.getPersonajes().add(personaje);
            peliculas.add(pelicula);
        }

        personaje.setPeliculas(peliculas);

        return repo.save(personaje);
    }

    @Override
    public List<Personaje> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Personaje> findById(Long id) throws PersonajeNotFoundException {
        Optional<Personaje> personaje = repo.findById(id);
        if(personaje.isEmpty()){
            throw new PersonajeNotFoundException("No se encontro el personaje con el id " + id);
        }
        return repo.findById(id);
    }

    @Override
    public Personaje actualizarPersonaje(Personaje personaje, Long id) throws PersonajeNotFoundException {
        Optional<Personaje> personajeDB = repo.findById(id);
        if(personajeDB.isEmpty()){
            throw new PersonajeNotFoundException("No se encontro el personaje");
        }
        Personaje personajeUpdate = new Personaje(id, personaje.getNombre(), personaje.getEdad(), personaje.getPeso(), personaje.getHistoria());
        return repo.save(personajeUpdate);
    }

    @Override
    public PersonajeDTO buscarPersonajePorNombre(String nombre) throws PersonajeNotFoundException {

        Personaje personaje = repo.buscarPorNombre(nombre);

        if(personaje == null){
            throw new PersonajeNotFoundException("No se encontro el personaje");
        }

        int edad = Period.between(personaje.getEdad(), LocalDate.now()).getYears();

        List<Pelicula> peliculas = personaje.getPeliculas();

        List<PeliculaDTO> peliculaDTOS = new ArrayList<>();

        for (Pelicula p : peliculas) {
            peliculaDTOS.add(new PeliculaDTO(p.getId(), p.getTitulo(), p.getFechaCreacion(), p.getCalificacion(), p.getGeneros()));
        }

        return new PersonajeDTO(personaje.getId(), personaje.getNombre(), edad, personaje.getPeso(), personaje.getHistoria(), peliculaDTOS);
    }

    @Override
    public void delete(Long id) throws PersonajeNotFoundException {

        Personaje personaje = repo.findById(id).orElseThrow(() -> new PersonajeNotFoundException("El personaje no existe"));

        repo.deleteById(id);
    }

    @Override
    public List<Personaje> filtrarPersonajesPorEdad(int edad) {
        return repo.filtrarPersonajesPorEdad(edad);
    }

    @Override
    public List<Personaje> filtrarPersonajesPorPelicula(Long id) {
        return repo.filtrarPersonajesPorPelicula(id);
    }


}
