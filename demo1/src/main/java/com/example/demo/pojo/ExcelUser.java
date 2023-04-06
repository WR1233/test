package com.example.demo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ExcelUser {

    // 定义表头名称和位置，0代表第一列
    @ExcelProperty(value = "编号", index = 0)
    private Integer userId;

    @ExcelProperty(value = "部门编号", index = 1)
    private Integer deptId;

    @ExcelProperty(value = "登录账号", index = 2)
    private String loginname;

    @ExcelProperty(value = "用户手机号", index = 3)
    private String phone;

    @ExcelProperty(value = "密码", index = 4)
    private String password;

    @ExcelProperty(value = "账户状态", index = 5)
    private Integer status;

    @ExcelProperty(value = "创建时间",index = 6)
    private Date createtime;

    @ExcelProperty(value = "更新时间",index = 7)
    private Date updatetime;

    private String sex;
}

