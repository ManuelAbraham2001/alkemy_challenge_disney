package com.manu.alkemy.disney.Controller;

import com.manu.alkemy.disney.Entities.Pelicula;
import com.manu.alkemy.disney.Exceptions.PeliculaNotFoundException;
import com.manu.alkemy.disney.Exceptions.PersonajeAlreadyExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNoExistsException;
import com.manu.alkemy.disney.Exceptions.PersonajeNotFoundException;
import com.manu.alkemy.disney.Services.PeliculaServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    PeliculaServiceImpl service;

    @PostMapping
    public ResponseEntity<?> crearPelicula(@RequestBody Pelicula pelicula){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.crearPelicula(pelicula));
        }catch (PersonajeNotFoundException e){
            Map<String, Object> error = new HashMap<>();
            error.put("Error", e.getMessage());
            error.put("Causa", "El personaje no existe");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula pelicula){
        try{
            return ResponseEntity.ok().body(service.actualizarPelicula(pelicula, id));
        }catch (PeliculaNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarPeliculaPorNombre(@RequestParam("name") String nombre) {
        try{
            return ResponseEntity.ok().body(service.buscarPeliculaPorNombre(nombre));
        }catch (PeliculaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }
    }

    @GetMapping(params = "genre")
    public ResponseEntity<?> buscarPeliculaPorGenero(@RequestParam("genre") String genero){
        try {
            return ResponseEntity.ok().body(service.buscarPeliculaPorGenero(genero));
        }catch (PeliculaNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }
    }

    @GetMapping(params = "order")
    public ResponseEntity<List<Pelicula>> ordenarPeliculas(@RequestParam("order") String order){
        if(order.equals("ASC")){
            return ResponseEntity.ok().body(service.ordenarPeliculasASC());
        } else if (order.equals("DESC")) {
            return ResponseEntity.ok().body(service.ordenarPeliculasDESC());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPelicula(@PathVariable Long id){
        try{
            service.eliminarPelicula(id);
            return ResponseEntity.ok().build();
        }catch (PeliculaNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }
    }

    @PutMapping("/{idPelicula}/characters/{idPersonaje}")
    public ResponseEntity<?> agregarPersonajePelicula(@PathVariable Long idPelicula, @PathVariable Long idPersonaje, HttpServletRequest request){

        try{
            service.agregarOEliminarPersonajePelicula(idPelicula, idPersonaje, request.getMethod());
            return ResponseEntity.ok().build();
        }catch (PeliculaNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }catch (PersonajeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (PersonajeAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }catch (PersonajeNoExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }

    }

    @DeleteMapping("/{idPelicula}/characters/{idPersonaje}")
    public ResponseEntity<?> eliminarPersonajePelicula(@PathVariable Long idPelicula, @PathVariable Long idPersonaje, HttpServletRequest request){
        try{
            service.agregarOEliminarPersonajePelicula(idPelicula, idPersonaje, request.getMethod());
            return ResponseEntity.ok().build();
        }catch (PeliculaNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }catch (PersonajeNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (PersonajeAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }catch (PersonajeNoExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getError());
        }
    }

}
