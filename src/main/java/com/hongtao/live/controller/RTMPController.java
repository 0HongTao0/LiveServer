package com.hongtao.live.controller;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created 2020/3/18.
 *
 * @author HongTao
 */
@Controller
@RequestMapping("/rtmp")
public class RTMPController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/on_connect")
    @ResponseBody
    public String connect(HttpServletRequest request) {
        debug(request, "on_connect");
        return "on_connect";
    }


    @RequestMapping(value = "/on_publish")
    @ResponseBody
    public String publish(HttpServletRequest request) {
        debug(request, "on_publish");
        return "on_publish";
    }

    @RequestMapping(value = "/on_publish_done")
    @ResponseBody
    public String publishDone(HttpServletRequest request) {
        debug(request, "on_publish_done");
        return "on_publish_done";
    }

    @RequestMapping(value = "/on_play")
    @ResponseBody
    public String play(HttpServletRequest request) {
        debug(request, "on_play");
        return "on_play";
    }

    @RequestMapping(value = "/on_play_done")
    @ResponseBody
    public String playDone(HttpServletRequest request) {
        debug(request, "on_play_done");
        return "on_play_done";
    }

    private String debug(HttpServletRequest request, String action) {
        String str = action + ": " + request.getRequestURI() + " " + request.getQueryString();
        logger.warn(str);
        return str;
    }
}
