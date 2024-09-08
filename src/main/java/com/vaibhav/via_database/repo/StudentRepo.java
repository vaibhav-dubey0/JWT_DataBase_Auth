package com.vaibhav.via_database.repo;

import com.vaibhav.via_database.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    Student findByName(String name);
}
