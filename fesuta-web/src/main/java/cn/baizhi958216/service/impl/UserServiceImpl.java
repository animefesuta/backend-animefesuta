package cn.baizhi958216.service.impl;

import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.enums.UserTypeEnum;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.UserService;
import cn.baizhi958216.viewobject.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserVO> getAllUsers() {
        List<UserDO> userDOList = userRepository.findAll();
        return userDOList.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public UserVO createUser(UserVO userVO) {
        UserDO userDO = new UserDO();
        userDO.setNickname(userVO.getNickname());
        userDO.setPassword(userVO.getPassword());
        userDO.setType(UserTypeEnum.USER.getType());
        userDO.setCreator(UserTypeEnum.USER.getType());
        userDO.setCreateTime(LocalDateTime.now());
        userDO.setUpdateTime(LocalDateTime.now());
        userDO = userRepository.save(userDO);
        return convertToVO(userDO);
    }

    private UserVO convertToVO(UserDO userDO) {
        UserVO userVO = new UserVO();
        userVO.setId(userDO.getId());
        userVO.setNickname(userDO.getNickname());
        userVO.setType(userDO.getType());
        userVO.setCreator(userDO.getCreator());
        userVO.setCreateTime(userDO.getCreateTime());
        userVO.setUpdateTime(userDO.getUpdateTime());
        return userVO;
    }
}
