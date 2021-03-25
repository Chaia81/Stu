package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student findById(Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isEmpty()){
            throw new RuntimeException("Student not found");
        }
        return studentOptional.get();
    }


    public List<Student> findAll(){
      List<Student> students = new ArrayList<Student>();
        for (Student student:studentRepository.findAll()){
            students.add(student);
      }
        return students;
    }


    public void create(Student student)
    {
        studentRepository.save(student);
    }

    public void update(Student student) {
        studentRepository.save(student); }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

}
