package com.qy23.sm.handler;

import com.qy23.sm.anntation.SexValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SexValiddator
 * @Author 刘伟
 * @Date 2020/10/21 19:44
 * @Description
 * @Version 1.0
 **/
public class SexValiddator implements ConstraintValidator<SexValues,String> {


    List<String> list;
    @Override
    public void initialize(SexValues constraintAnnotation) {
        //初始化
        //values注解上自己指定的值
        String[] values = constraintAnnotation.values();
        list=Arrays.asList(values);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //验证
        //value用户传的值
        return list.contains(value);
    }
}
