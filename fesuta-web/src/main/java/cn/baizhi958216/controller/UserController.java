package cn.baizhi958216.controller;

import cn.baizhi958216.service.UserService;
import cn.baizhi958216.viewobject.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/fesuta/user/fetchUsersList")
    public List<UserVO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/fesuta/user/createUser")
    public UserVO createUser(@RequestBody UserVO userVO) {
        return userService.createUser(userVO);
    }
}
