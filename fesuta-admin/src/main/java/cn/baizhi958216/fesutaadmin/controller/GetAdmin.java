package cn.baizhi958216.fesutaadmin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getadmin")
public class GetAdmin {
    @GetMapping("/")
    String admin(){
        return "Admin Page";
    }
}
