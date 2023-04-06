package com.example.demo.dao;

import com.example.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> selectAll();

    int insert(Student student);
}

