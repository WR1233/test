package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String id;
    private String name;
    private Double score;
    private Integer groupId;

    public Student(String zhangsan, double v, int i) {
    }
}
//导入 创键excel实体-》读出实体数据->对读出的数据进行格式转换->将实体封装-》根据实体名称判断数据库是否有数据
// ->存在数据就进行更新 不存在数据就插入

//数据库查询需要导出的数据-》对数据进行格式转换