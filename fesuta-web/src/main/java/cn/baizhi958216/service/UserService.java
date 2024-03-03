package cn.baizhi958216.service;

import cn.baizhi958216.viewobject.UserVO;

import java.util.List;

public interface UserService {
    List<UserVO> getAllUsers();
    UserVO createUser(UserVO userVO);
}
