package com.manu.alkemy.disney.Services;

import com.manu.alkemy.disney.Entities.Genero;
import com.manu.alkemy.disney.Repositories.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService{

    @Autowired
    GeneroRepository repo;
    @Override
    public Genero crear(Genero genero) {
        return repo.save(genero);
    }
}
