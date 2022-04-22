package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StudentController {

  private final StudentRepository studentRepository;


  @PostConstruct
  private void postConstruct() {
    Student engStudent = new Student(
        "Eng2015001", "John Doe", Student.Gender.MALE, 1);
    Student medStudent = new Student(
        "Med2015001", "Gareth Houston", Student.Gender.MALE, 2);
    studentRepository.save(engStudent);
    studentRepository.save(medStudent);
  }

  @GetMapping("/")
  public ResponseEntity getAllStudent() {
    List<Student> students = new ArrayList<>();
    studentRepository.findAll().forEach(students::add);
    return new ResponseEntity<>(students,HttpStatus.ACCEPTED);
  }

  @GetMapping("/{id}")
  public ResponseEntity getStudentById(@PathVariable("id") String id ) {
    Optional<Student> student=studentRepository.findById(id);
    return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
  }

  @PostMapping("/")
  public ResponseEntity addStudent(@RequestBody Student student) {
    Student newStudent=studentRepository.save(student);
    return new ResponseEntity<>(newStudent,HttpStatus.ACCEPTED);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateStudent(@PathVariable("id") String id,@RequestBody Student student) {
    Student newStudent=studentRepository.save(student);
    return new ResponseEntity<>(newStudent,HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteStudent(@PathVariable("id") String id) {
    studentRepository.deleteById(id);
    return new ResponseEntity<>(id,HttpStatus.ACCEPTED);
  }

}
