package com.manu.alkemy.disney.Services;

import com.manu.alkemy.disney.Entities.Usuario;
import com.manu.alkemy.disney.Repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Usuario registrar(Usuario usuario) {

        if(repo.existsByUsername(usuario.getUsername())){
            throw new EntityNotFoundException("El usuario ya existe");
        }

        Usuario user = new Usuario();

        user.setEmail(usuario.getEmail());
        user.setPassword(passwordEncoder.encode(usuario.getPassword()));
        user.setUsername(usuario.getUsername());

        return repo.save(user);
    }
}
