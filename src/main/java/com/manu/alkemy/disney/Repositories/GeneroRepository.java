package com.manu.alkemy.disney.Repositories;

import com.manu.alkemy.disney.Entities.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
