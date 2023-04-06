package com.example.demo.controller;

import com.example.demo.pojo.ExcelUser;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class ExcelController {

    public static void main(String[] args) {
        System.out.println("git测试代码提交");
        System.out.println("------------------------");
    }

    @Autowired
    private ExcelService excelService;

    /**
     * 导出用户信息
     *
     * @param response response
     * @return ResultMap
     */
    @GetMapping(path = "/excelExport")
    @ResponseBody
    public ResultMap excelExport(HttpServletResponse response) {
        return excelService.excelExport(response);
    }

    @GetMapping(path = "/fileUpload")
    public String uploadPage(){
        return "fileUpload";
    }

    /**
     * 导入用户信息
     *
     * @param file multipartFile
     * @return ResultMap
     */
    @PostMapping(path = "/excelImport")
    @ResponseBody
    public ResultMap excelImport(MultipartFile file) {
        return excelService.excelImport(file);
    }


    @PostMapping(path = "/insert")
    @ResponseBody
    public ResultMap insert(@RequestBody ExcelUser user) {
        return excelService.insert(user);
    }
}

