package com.example.rest.webservices.controller;

import com.example.rest.webservices.bean.HelloWorldBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    public String helloWorld() {
        logger.info("helloWorld method called..");
        return "Hello - World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        logger.info("helloWorldBean method called..");
        return new HelloWorldBean("Hello-World-Message");
    }

    @GetMapping("/hello-world-bean/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name){
        logger.info("helloWorldBeanPathVariable method called..");
        return new HelloWorldBean(String.format("Hello World Bean , %s", name));
    }


}

