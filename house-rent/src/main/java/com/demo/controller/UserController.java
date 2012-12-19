package com.demo.controller;

import com.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-20
 * Time: 上午12:25
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class UserController {

    @Resource
    UserRepository userRepository;


}
