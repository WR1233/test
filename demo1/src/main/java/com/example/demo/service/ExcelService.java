package com.example.demo.service;

import com.example.demo.pojo.ExcelUser;
import com.example.demo.utils.ResultMap;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


public interface ExcelService{

    ResultMap excelExport(@NotNull HttpServletResponse response);

    ResultMap excelImport(@NotNull MultipartFile multipartFile);

    ResultMap insert(ExcelUser user);
}
