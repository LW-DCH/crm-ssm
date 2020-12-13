package com.qy23.sm.anntation;

import com.qy23.sm.handler.SexValiddator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 性别区分注解
 * @author dn19
 */
//运行在哪个层
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
//生效的时间
@Retention(RetentionPolicy.RUNTIME)
//指定验证器
@Constraint(validatedBy = {SexValiddator.class})
public @interface SexValues {


    String message() default "{javax.validation.constraints.Pattern.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] values() default{};
}
