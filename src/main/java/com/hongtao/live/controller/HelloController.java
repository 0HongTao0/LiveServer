package com.hongtao.live.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * Created 2020/3/12.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/demo")
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        System.out.println(userId);
        return "helloTest";
    }
}
