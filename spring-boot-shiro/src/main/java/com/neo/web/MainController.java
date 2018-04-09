package com.neo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fx on 2017/12/29.
 */
@Controller
@RequestMapping("/index")
public class MainController {

    @RequestMapping("/index")
    public String html(){

        return "index/index";
    }

}
