package com.example.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.example.demo.dao.ExcelUserMapper;
import com.example.demo.listener.ExcelListener;
import com.example.demo.pojo.ExcelUser;
import com.example.demo.pojo.ExcelUserDto;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.ResultMap;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelUserMapper excelUserMapper;

    /**
     * 导出用户信息
     *
     * @param response response
     */
    @Override
    public ResultMap excelExport(@NotNull HttpServletResponse response) {
        // 获取需要导出的数据
        List<ExcelUser> excelUserList = excelUserMapper.getAll();
        log.info("记录导出数据行数：{}", excelUserList.size());
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String fileName = URLEncoder.encode("用户名单表", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 向Excel中写入数据
            ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(), ExcelUser.class);
            //获取需要写入的工作表
            ExcelWriterSheetBuilder excelWriterSheetBuilder = write.sheet("用户名单表");

            //向工作表中写入数据
            excelWriterSheetBuilder.doWrite(excelUserList);
            return new ResultMap().success();
        } catch (Exception e) {
            log.error("数据导出失败！！！");
            return new ResultMap().fail();
        }
    }

    /**
     * 导入用户信息
     *
     * @param multipartFile multipartFile
     */
    @Transactional
    @Override
    public ResultMap excelImport(@NotNull MultipartFile multipartFile) {
        try {
            ExcelListener excelListener = new ExcelListener(); //读监听器 每读取一条excel中的数据 调用一次invoke方法 操作读取的每一行数据
            // 读取数据
            ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(multipartFile.getInputStream(), ExcelUser.class, excelListener);
            ExcelReaderSheetBuilder excelReaderSheetBuilder = excelReaderBuilder.sheet();
            excelReaderSheetBuilder.doRead();
            // 获取excel读取到的数据
            List<ExcelUser> list = excelListener.getList();
            // 比较loginname字段去重，数据库有唯一索引
            List<ExcelUser> excelUserList = this.removeDuplicateOrder(list);
            // 数据库表不用插入userId列，它是主键列
            List<ExcelUserDto> excelUserDtoList = new ArrayList<>();
            excelUserList.forEach(item -> {
                ExcelUserDto excelUserDto = new ExcelUserDto();
                BeanUtils.copyProperties(item, excelUserDto);
                excelUserDtoList.add(excelUserDto);
            });
            // 数据的持久化
            int i = excelUserMapper.insertAll(excelUserDtoList);
            if (i > 0) {
                return new ResultMap().success();
            }
            return new ResultMap().fail();
        } catch (IOException e) {
            log.error("数据导入失败！！！");
            return new ResultMap().fail();
        }
    }

    /**
     * 比较loginname字段去重，数据库有唯一索引
     *
     * @param list list
     * @return List
     */
    @NotNull
    private List<ExcelUser> removeDuplicateOrder(List<ExcelUser> list) {
        Set<ExcelUser> set = new TreeSet<>((a, b) -> {
            // 等于0表示重复
            int compareToResult = 1;
            if (a.getLoginname().equals(b.getLoginname())) {
                compareToResult = 0;
            }
            return compareToResult;
        });
        set.addAll(list);
        return new ArrayList<>(set);
    }

    @Override
    public ResultMap insert(ExcelUser user) {
        return excelUserMapper.insert(user);
    }
}

