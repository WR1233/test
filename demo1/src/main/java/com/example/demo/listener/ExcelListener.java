package com.example.demo.listener;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.pojo.ExcelUser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelListener extends AnalysisEventListener<ExcelUser> {

    @Getter
    @Setter
    private List<ExcelUser> list = new ArrayList<>();

    @Override
    public void invoke(ExcelUser excelUser, AnalysisContext analysisContext) {
        log.info("--------------------------------------------");
        log.info("导入数据{}", JSONUtils.toJSONString(excelUser)); //JSON.toJSONString()
        // 数据存储到list，供批量处理，或后续自己业务逻辑处理
        list.add(excelUser);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

}
