package com.example.demo;

import com.example.demo.dao.StudentMapper;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import com.example.demo.utils.ResultMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;


@SpringBootTest
public class Demo1ApplicationTests {

 /*   @Resource
    private DataSource dataSource;

    @Test
    void JDBCTest() throws SQLException {
        System.err.println(dataSource.getClass());
    }*/

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Test
    void selectAllTest(){

        Student student = new Student("123","zhangsan",99.0,1);
        studentMapper.insert(student);
    }

}
