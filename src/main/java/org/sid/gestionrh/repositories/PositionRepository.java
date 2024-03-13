package org.sid.gestionrh.repositories;

import org.sid.gestionrh.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position,Long> {
    Optional<Position> findByTitleAndDescription(String title,String description);
    Optional<Position> findByTitle(String title);
}
