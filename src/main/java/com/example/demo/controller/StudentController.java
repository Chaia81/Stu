package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {


    StudentService studentService;
    StudentRepository studentRepository;

    public StudentController(StudentService studentService,
                             StudentRepository studentRepository){
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }


    /*----------------------------------------- Read all Students ---------------------------------------------------*/

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("students", studentService.findAll());
        return "index";
    }

    /*-------------------------------------------- Create Student ---------------------------------------------------*/

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute Student student){
        studentRepository.save(student);
        //System.out.println(student);
        return "redirect:/";
    }

    /*--------------------------------------------- Update student --------------------------------------------------*/

    @GetMapping("/updateStudent")
    public String updateStudent(Model model, @RequestParam Long id){
        model.addAttribute("student", studentService.findById(id));
        return "/student/updateStudent";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student){
        studentRepository.save(student);
        return "redirect:/";
    }

    /*------------------------------------------- Delete Student ----------------------------------------------------*/

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long id){
        studentRepository.deleteById(id);
        //Optional<Student> studentOptional = studentRepository.findById(id);
        //model.addAttribute("student", studentOptional);
        return "redirect:/";
    }



}
