package com.manu.alkemy.disney.Controller;

import com.manu.alkemy.disney.Entities.Dto.JwtTokenDTO;
import com.manu.alkemy.disney.Entities.Usuario;
import com.manu.alkemy.disney.Security.JwtGenerador;
import com.manu.alkemy.disney.Services.EmailService;
import com.manu.alkemy.disney.Services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerador jwtGenerador;

    @Autowired
    private EmailService emailService;


    @PostMapping("/register")
    public ResponseEntity<?> registro(@RequestBody Usuario usuario){

//        emailService.sendMail(usuario.getEmail(), "Bienvenido", "Te registraste en la api de Disney");

        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDTO> login(@RequestBody Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                usuario.getUsername(), usuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerador.generarToken(authentication);

        return new ResponseEntity<>(new JwtTokenDTO(token), HttpStatus.OK);
    }

}

