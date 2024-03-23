package cn.baizhi958216.service;

import cn.baizhi958216.viewobject.UserVO;

import java.util.List;

public interface UserService {
    List<UserVO> getAllUsers();

    UserVO createUser(UserVO userVO);

    UserVO findUserByUUID(String uuid);

    List<UserVO> findUserByNickName(String nickname);

    List<UserVO> findUserByNickNameFuzzy(String nickname);

    Boolean deleteUserByUUID(String uuid);

    UserVO updateUserAvatar(String link);

    UserVO updateUserByUUID(UserVO userVO);

    UserVO findUserByEmail(String useremail);

    UserVO updateUserInstruction(String instruction);

    UserVO updateUserNickName(String nickname);

    UserVO updateUserBackground(String background);

    UserVO updateUserEmail(String email);
}
