package com.manu.alkemy.disney.Services;

import com.manu.alkemy.disney.Entities.Pelicula;
import com.manu.alkemy.disney.Entities.Personaje;
import com.manu.alkemy.disney.Exceptions.PeliculaNotFoundException;
import com.manu.alkemy.disney.Exceptions.PersonajeAlreadyExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNoExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNotFoundException;
import com.manu.alkemy.disney.Repositories.PeliculaRepository;
import com.manu.alkemy.disney.Repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService{

    @Autowired
    PeliculaRepository repo;

    @Autowired
    PersonajeRepository personajeRepo;

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws PersonajeNotFoundException {

        List<Personaje> personajesPelicula = new ArrayList<>();

        for (Personaje p : pelicula.getPersonajes()) {
            Personaje personajeExistente = personajeRepo.buscarPorNombre(p.getNombre());
            if(personajeExistente != null){
                personajesPelicula.add(personajeExistente);
            }else{
                throw new PersonajeNotFoundException("No se encontro el personaje " + p.getNombre());
            }
        }

        pelicula.setPersonajes(personajesPelicula);

        return repo.save(pelicula);
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula, Long id) throws PeliculaNotFoundException {
        Optional<Pelicula> peliculaDB = repo.findById(id);

        if(peliculaDB.isPresent()){
            Pelicula peliculaActualizada = new Pelicula(id, pelicula.getTitulo(), pelicula.getFechaCreacion(), pelicula.getCalificacion(), pelicula.getGeneros());
            peliculaActualizada.setPersonajes(peliculaDB.get().getPersonajes());
            repo.save(peliculaActualizada);
            return peliculaActualizada;
        }else{
            throw new PeliculaNotFoundException("No se encontro la pelicula", "No existe una pelicula con ese ID");
        }
    }

    @Override
    public Pelicula buscarPeliculaPorNombre(String nombre) throws PeliculaNotFoundException {

        Pelicula pelicula = repo.buscarPeliculaPorNombre(nombre);

        if(pelicula != null){
            return pelicula;
        }else{
            throw new PeliculaNotFoundException("No se encontro la pelicula", "No existe una pelicula con el nombre " + nombre);
        }
    }

    @Override
    public List<Pelicula> buscarPeliculaPorGenero(String genero) throws PeliculaNotFoundException {

        List<Pelicula> peliculas = repo.buscarPeliculaPorGenero(genero);

        if(!peliculas.isEmpty()){
            return peliculas;
        }else{
            throw new PeliculaNotFoundException("No se encontraron peliculas", "No hay ninguna pelicula con el genero " + genero);
        }
    }

    @Override
    public List<Pelicula> ordenarPeliculasASC() {
        return repo.ordenarPeliculasASC();
    }

    @Override
    public List<Pelicula> ordenarPeliculasDESC() {
        return repo.ordenarPeliculasDESC();
    }

    @Override
    public void eliminarPelicula(Long id) throws PeliculaNotFoundException {
        Optional<Pelicula> pelicula = repo.findById(id);
        if(pelicula.isPresent()){
            repo.deleteById(id);
        }else{
            throw new PeliculaNotFoundException("No se pudo eliminar la pelicula", "La pelicula con el ID " + id + " no existe");
        }
    }

    @Override
    public void agregarOEliminarPersonajePelicula(Long idPelicula, Long idPersonaje, String metodo) throws PersonajeNotFoundException, PeliculaNotFoundException, PersonajeAlreadyExistsException, PersonajeNoExistsException {

        Personaje personaje = personajeRepo.findById(idPelicula).orElseThrow(() -> new PersonajeNotFoundException("Personaje no encontrado"));
        Pelicula pelicula = repo.findById(idPelicula).orElseThrow(() -> new PeliculaNotFoundException("Pelicula no encontrada", "No existe una pelicula con el ID " + idPelicula));

        List<Personaje> personajes = pelicula.getPersonajes();

        if (metodo.equals("PUT")) {
            if (personajes.contains(personaje)) {
                throw new PersonajeAlreadyExistsException("No se pudo agregar el personaje a la pelicula", "El personaje ya existe en la pelicula");
            }
            personajes.add(personaje);
        } else if (metodo.equals("DELETE")) {
            if (!personajes.contains(personaje)) {
                throw new PersonajeNoExistsException("No se pudo eliminar el personaje a la pelicula", "El personaje " + personaje.getNombre() + " no se encuentra en la película ");
            }
            personajes.remove(personaje);
        } else {
            throw new IllegalArgumentException("Método HTTP no válido");
        }

        Pelicula peliculaActualizada = new Pelicula(idPelicula, pelicula.getTitulo(), pelicula.getFechaCreacion(),
                pelicula.getCalificacion(), pelicula.getGeneros(), personajes);
        repo.save(peliculaActualizada);
    }

}
