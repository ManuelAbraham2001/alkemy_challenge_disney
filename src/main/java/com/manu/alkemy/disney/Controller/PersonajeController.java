package com.manu.alkemy.disney.Controller;

import com.manu.alkemy.disney.Entities.Dto.PersonajeDTO;
import com.manu.alkemy.disney.Entities.Personaje;
import com.manu.alkemy.disney.Exceptions.PeliculaNotFoundException;
import com.manu.alkemy.disney.Exceptions.PersonajeAlreadyExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNoExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNotFoundException;
import com.manu.alkemy.disney.Services.PersonajeServiceImpl;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    PersonajeServiceImpl service;

    @PostMapping
    public ResponseEntity<?> crearPersonaje(@RequestBody Personaje personaje) throws PeliculaNotFoundException {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.crearPersonaje(personaje));
        }catch (PeliculaNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        } catch (PersonajeAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (PersonajeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPersonaje(@PathVariable Long id, @RequestBody Personaje personaje) throws PersonajeNotFoundException {

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.actualizarPersonaje(personaje, id));

        }catch (PersonajeNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPersonaje(@PathVariable Long id) throws PersonajeNotFoundException {
        try{
            service.delete(id);
            return ResponseEntity.ok().body("Personaje eliminado");
        }catch (PersonajeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarPersonajePorNombre(@RequestParam("name") String nombre){
        try{
            return ResponseEntity.ok().body(service.buscarPersonajePorNombre(nombre));
        }catch (PersonajeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe un personaje con el nombre " + nombre);
        }
    }

    @GetMapping(params = "age")
    public ResponseEntity<?> filtrarPorEdad(@RequestParam("age") int edad){
        List<Personaje> personajes = service.filtrarPersonajesPorEdad(edad);
        return ResponseEntity.ok().body(personajes);
    }

    @GetMapping(params = "movie")
    public ResponseEntity<?> filtrarPorPelicula(@RequestParam("movie") Long id){
        List<Personaje> personajes = service.filtrarPersonajesPorPelicula(id);
        return ResponseEntity.ok().body(personajes);
    }

}
