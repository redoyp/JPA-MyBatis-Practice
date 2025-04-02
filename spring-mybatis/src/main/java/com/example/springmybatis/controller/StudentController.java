package com.example.springmybatis.controller;

import com.example.springmybatis.model.Students;
import com.example.springmybatis.service.StudentService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/count")
    public int countStudents() {
        return studentService.countAll();
    }

    @GetMapping("/students")
    public List<Students> getStudentList(@RequestParam("id") int id) {
        return studentService.selectStudents(id);
    }

//    @GetMapping("/students/add")
//    public List<Students> addStudent(@RequestBody Students student) {
//        return studentService.insertStudents(student);
//    }
}
