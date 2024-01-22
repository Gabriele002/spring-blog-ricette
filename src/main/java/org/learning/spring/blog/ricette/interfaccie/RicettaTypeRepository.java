package org.learning.spring.blog.ricette.interfaccie;

import org.learning.spring.blog.ricette.model.RicettaType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RicettaTypeRepository extends JpaRepository<RicettaType, Integer> {
}
