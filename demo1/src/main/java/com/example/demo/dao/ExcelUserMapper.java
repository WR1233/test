package com.example.demo.dao;

import com.example.demo.pojo.ExcelUser;
import com.example.demo.pojo.ExcelUserDto;
import com.example.demo.utils.ResultMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ExcelUserMapper {

    List<ExcelUser> getAll();

    int insertAll(List<ExcelUserDto> excelUserDtoList);

    ResultMap insert(ExcelUser user);
}
