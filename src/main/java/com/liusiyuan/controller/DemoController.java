package com.liusiyuan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 测试方法
 * @author liusy
 * @Date 2017/12/13 9:43
 * @param
 * @return
 */
@Controller
@RequestMapping("/liu")
public class DemoController {
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/test1")
    public ModelAndView init(){
        logger.info("这是我的第一个logger日志");
        return new ModelAndView("index");
    }
}
