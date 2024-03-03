package cn.baizhi958216.controller;

import cn.baizhi958216.service.UserService;
import cn.baizhi958216.viewobject.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/fesuta/user/fetchUsersList")
    public List<UserVO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/fesuta/user/createUser")
    public UserVO createUser(@RequestBody UserVO userVO) {
        return userService.createUser(userVO);
    }

    @PostMapping("/fesuta/user/findUserByUUID")
    public UserVO findUserByUUID(@RequestBody UserVO userVO) {
        return userService.findUserByUUID(userVO.getId());
    }

    @PostMapping("/fesuta/user/findUserByNickname")
    public List<UserVO> findUserByNickname(@RequestBody UserVO userVO) {
        return userService.findUserByNickName(userVO.getNickname());
    }

    @PostMapping("/fesuta/user/findUserByNicknameFuzzy")
    public List<UserVO> findUserByNicknameFuzzy(@RequestBody UserVO userVO) {
        return userService.findUserByNickNameFuzzy(userVO.getNickname());
    }

    @PostMapping("/fesuta/user/deleteUserByUUID")
    public Boolean deleteUserByUUID(@RequestBody UserVO userVO) {
        return userService.deleteUserByUUID(userVO.getId());
    }

    @PostMapping("/fesuta/user/updateUserByUUID")
    public UserVO updateUserByUUID(@RequestBody UserVO userVO) {
        return userService.updateUserByUUID(userVO);
    }

}
