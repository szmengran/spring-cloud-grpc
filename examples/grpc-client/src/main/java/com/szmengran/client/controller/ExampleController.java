package com.szmengran.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.szmengran.client.service.ExampleService;

/**
 * @Description Rest测试服务
 * @Date 2020/4/15 9:06 上午
 * @Author <a href="mailto:android_li@sina.cn">Joe</a>
 **/
@RestController
public class ExampleController {
    
    @Autowired
    private ExampleService exampleService;
    
    @GetMapping("/send/{name}")
    public String sendMessage(@PathVariable("name") String name) {
        return exampleService.sendMessage(name);
    }
}
