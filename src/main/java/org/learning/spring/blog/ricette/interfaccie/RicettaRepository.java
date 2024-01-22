package org.learning.spring.blog.ricette.interfaccie;

import org.learning.spring.blog.ricette.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RicettaRepository extends JpaRepository<Ricetta, Integer> {
    List<Ricetta> findByNameContaining (String search);
}
