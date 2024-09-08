package com.vaibhav.via_database.controller;

import com.vaibhav.via_database.model.Student;
import com.vaibhav.via_database.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    // Get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // Get student by ID
    @GetMapping("student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new student
    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    // Update an existing student
    @PutMapping("student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(studentDetails.getName());
            student.setMarks(studentDetails.getMarks());
            Student updatedStudent = studentRepo.save(student);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a student
    @DeleteMapping("student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        Optional<Student> student = studentRepo.findById(id);
        
        if (student.isPresent()) {
            studentRepo.delete(student.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get student by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        Student student = studentRepo.findByName(name);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

