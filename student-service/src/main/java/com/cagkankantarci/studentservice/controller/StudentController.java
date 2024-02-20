package com.cagkankantarci.studentservice.controller;

import com.cagkankantarci.studentservice.model.Student;
import com.cagkankantarci.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentRepository.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentRepository.findById(id);
    }

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/lesson/{lessonId}")
    public List<Student> findByLessonId(@PathVariable("lessonId") Long lessonId){
        return studentRepository.findByLessonId(lessonId);
    }
}
