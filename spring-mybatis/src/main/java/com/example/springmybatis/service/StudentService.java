package com.example.springmybatis.service;

import com.example.springmybatis.model.Students;
import com.example.springmybatis.repository.StudentMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public int countAll() {
        return studentMapper.countStudents();
    }

    public List<Students> selectStudents(int id) {
        return studentMapper.selectStudents(id);
    }

    public int saveStudent(Students students) {
        return studentMapper.saveStudent(students);
    }

    public int updateStudent(Students students) {
        return studentMapper.updateStudent(students);
    }

    public int deleteStudent(int id) { return studentMapper.deleteStudent(id); }
}
