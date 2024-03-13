package org.sid.gestionrh.repositories;

import org.sid.gestionrh.entities.Department;
import org.sid.gestionrh.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Optional<Department> findByNameAndLocation(String name, String location);
    Optional<Department> findByLocation(String location);
    Optional<Department> findByName(String name);
}
