package com.qy23.sm.http;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName PageResult
 * @Author 刘伟
 * @Date 2020/10/17 21:38
 * @Description
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class PageResult {

    private Object records;
    private Long total;

    public static PageResult instance(Object records, Long total) {
        return new PageResult(records, total);
    }

}
