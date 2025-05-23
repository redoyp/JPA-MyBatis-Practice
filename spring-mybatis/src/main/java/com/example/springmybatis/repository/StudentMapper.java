package com.example.springmybatis.repository;

import com.example.springmybatis.model.Students;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper // SQL 쿼리와 연결되는 어노테이션
public interface StudentMapper {
    // 각 쿼리의 id를 메소드로 만들기
    int countStudents();

    // 전체 select
    List<Students> selectStudents(int id);

    // insert
    // int : insert된 row 수 출력해주기 위해
    int saveStudent(Students student);

    int updateStudent(Students students);

    int deleteStudent(int id);
}
