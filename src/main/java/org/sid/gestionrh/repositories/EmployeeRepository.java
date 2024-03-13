package org.sid.gestionrh.repositories;

import org.sid.gestionrh.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<Employee> findByEmail(String email);

    @Query(value = "SELECT *FROM Employees AS e WHERE e.department_id = ?1",nativeQuery = true)
    List<Employee> findByDepartment(Long id);

    @Query(value = "SELECT *FROM Employees AS e WHERE e.position_id = ?1",nativeQuery = true)
    List<Employee> findByPosition(Long id);

    @Query(value = "SELECT * FROM Employees AS e WHERE e.employee_date BETWEEN ?1 AND ?2", nativeQuery = true)
    Optional<List<Employee>> findByDate(Date date1,Date date2);
}

