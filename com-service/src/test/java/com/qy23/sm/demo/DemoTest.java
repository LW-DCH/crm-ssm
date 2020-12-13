package com.qy23.sm.demo;

import com.qy23.sm.entity.BaseGood;
import com.qy23.sm.service.IBaseCategoryService;
import com.qy23.sm.service.IBaseGoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName DemoTest
 * @Author 刘伟
 * @Date 2020/10/16 20:38
 * @Description
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-*.xml", "classpath:applicationContext-service.xml"})
public class DemoTest {

    @Autowired
    private IBaseCategoryService iBaseCategoryService;
    @Autowired
    private IBaseGoodService iBaseGoodService;


    @Test
    public void doTest() {
        System.out.println(iBaseCategoryService.getCategoryTree());
    }
    @Test
    public void doTest1() {
        List<BaseGood> all = iBaseGoodService.findAll();

        System.out.println(all);
    }
}
