package com.njtech.mmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainHandler {

    @GetMapping("/{url}")
    public String toMain(@PathVariable("url") String url){
        return url;
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/productCategory/list";
    }
}
