package org.sid.gestionrh.repositories;

import org.sid.gestionrh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByEmail(String email);
}
