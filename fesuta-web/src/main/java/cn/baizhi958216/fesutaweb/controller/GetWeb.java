package cn.baizhi958216.fesutaweb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getweb")
public class GetWeb {
    @GetMapping("/")
    String index(){
        return "Index Page";
    }
}
