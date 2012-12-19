package com.demo.controller;

import com.demo.repository.HouseRepository;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-20
 * Time: 上午12:26
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HouseController {

    @Resource
    HouseRepository houseRepository;

}
