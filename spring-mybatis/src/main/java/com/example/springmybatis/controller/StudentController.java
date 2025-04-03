package com.example.springmybatis.controller;

import com.example.springmybatis.model.Students;
import com.example.springmybatis.service.StudentService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // POST /students
    @PostMapping("/students")
    public int saveStudent(@RequestBody Students student) {
        int saveCount = studentService.saveStudent(student);
        return saveCount;
    }

    // PUT /students/{id}    {name, age, address}
    @PutMapping("/students/{id}")
    public int updateStudent(@PathVariable Integer id,
             @RequestBody Students students) {
        students.setId(id);
        return studentService.updateStudent(students);
    }

    // DELETE /students/{id}
    @DeleteMapping("/students/{id}")
    public int deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }
}
