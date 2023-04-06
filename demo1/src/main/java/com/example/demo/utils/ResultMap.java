package com.example.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.Result;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMap {
    private String msg;
    private String code;
    private Object Data;

    public static ResultMap success(){
        ResultMap  resultMap = new ResultMap();
        resultMap.setCode("200");
        resultMap.setData("ok");
        return resultMap;
    }
    public static ResultMap fail(){
        ResultMap  resultMap = new ResultMap();
        resultMap.setCode("500");
        resultMap.setMsg("fail");
        return resultMap;
    }
}
