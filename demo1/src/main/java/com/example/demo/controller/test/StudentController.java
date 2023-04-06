package com.example.demo.controller.test;

import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/selectAll")
    public ResultMap selectAll(){
       List<Student> studentList = studentService.selectAll();
       return ResultMap.success();
    }
}
