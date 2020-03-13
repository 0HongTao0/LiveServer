package com.hongtao.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created 2020/3/12.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/demo")
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "home";
    }
}
