package org.learning.spring.blog.ricette.interfaccie;

import org.learning.spring.blog.ricette.model.Ricetta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RicettaRepository extends JpaRepository<Ricetta, Integer> {
}
