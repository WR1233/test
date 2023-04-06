package com.example.demo.service.impl;

import com.example.demo.dao.StudentMapper;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> selectAll() {
        List<Student> students = studentMapper.selectAll();
        List<Student> stu = new ArrayList<>();
        for (Student student : students) {
            if (student.getScore()>90)
            {
               stu.add(student);
            }
        }

        return stu;
    }
}
