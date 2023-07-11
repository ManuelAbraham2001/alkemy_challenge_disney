package com.manu.alkemy.disney.Security;

import com.manu.alkemy.disney.Entities.Usuario;
import com.manu.alkemy.disney.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUsersDetailsService implements UserDetailsService  {
    private UsuarioRepository repo;

    @Autowired
    public CustomUsersDetailsService(UsuarioRepository usuariosRepo) {
        this.repo = usuariosRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new User(usuario.getUsername(), usuario.getPassword(), Collections.emptyList());
    }
}
