package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SupervisorRepository;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestStudentController {

    StudentRepository studentRepository;
    SupervisorRepository supervisorRepository;

    public RestStudentController(StudentRepository studentRepository,
                                 SupervisorRepository supervisorRepository) {
        this.studentRepository = studentRepository;
        this.supervisorRepository = supervisorRepository;
    }

    /*----------------------------------------- HTTP POST aka create ------------------------------------------------*/

    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value = "/student", consumes={"application/json"})
    public ResponseEntity<String> create(@RequestBody Student s){
        Student _student = new Student(s.getName(), s.getEmail(), s.getSupervisor());
        studentRepository.save(_student);
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/student" + s.getId()).body("{'Msg': 'post created'}");
    }

    /*---------------------------------------- HTTP GET BY ID aka read ----------------------------------------------*/

    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(student);
        }
    }

    /*---------------------------------------- HTTP GET LIST aka find all -------------------------------------------*/

    @GetMapping("/student")
    public ResponseEntity<Iterable<Student>> findAll(){
            Iterable<Student> students = studentRepository.findAll();
            return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /*------------------------------------------- HTTP PUT aka update -----------------------------------------------*/

    @PutMapping("student/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Student s){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg':'Not found'}");
        }
        studentRepository.save(s);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{'msg':'Updated'}");
    }

    /*----------------------------------------------- HTTP Delete ---------------------------------------------------*/

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg':'Not found'}");
        }
        studentRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("{'msg':'Deleted'}");
    }

}
