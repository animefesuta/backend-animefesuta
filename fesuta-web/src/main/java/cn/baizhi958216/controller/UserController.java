package cn.baizhi958216.controller;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.UserService;
import cn.baizhi958216.viewobject.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@ResponseResultBody
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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

    @PostMapping("/fesuta/user/findUserByEmail")
    public UserVO findUserByEmail(@RequestBody UserVO userVO) {
        return userService.findUserByEmail(userVO.getEmail());
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

    @PostMapping("/fesuta/user/updateAvatar")
    public UserVO updateAvatar(@RequestBody UserVO userVO) {
        return userService.updateUserAvatar(userVO.getAvatar());
    }

    @PostMapping("/fesuta/user/updateInstruction")
    public UserVO updateInstruction(@RequestBody UserVO userVO) {
        return userService.updateUserInstruction(userVO.getInstruction());
    }

    @PostMapping("/fesuta/user/updateNickName")
    public UserVO updateUserNickName(@RequestBody UserVO userVO) {
        return userService.updateUserNickName(userVO.getNickname());
    }

}
