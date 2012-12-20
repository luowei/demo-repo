package com.demo;

import com.demo.repository.HouseRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-20
 * Time: 上午12:54
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-root.xml" })
@Transactional
public class TestUser {

    @Autowired
    HouseRepository houseRepository;

    @Test
    public void testHouseRepository() throws Exception {

        Assert.assertNotNull(houseRepository);
        for(Method method:houseRepository.getClass().getMethods())
        System.out.println(method.getName());
    }
}
