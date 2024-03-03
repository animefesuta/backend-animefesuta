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
import java.util.Optional;
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

    @Override
    public UserVO findUserByUUID(String uuid) {
        Optional<UserDO> userOptional = userRepository.findById(uuid);
        if (userOptional.isPresent()) {
            return convertToVO(userOptional.get());
        } else {
            return null;
        }
    }

    @Override
    public List<UserVO> findUserByNickName(String nickname) {
        List<UserDO> userDOList;
        userDOList = userRepository.findAllByNickname(nickname);
        return userDOList.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public List<UserVO> findUserByNickNameFuzzy(String nickname) {
        List<UserDO> userDOList;
        userDOList = userRepository.findAllByNicknameLike("%" + nickname + "%");
        return userDOList.stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public Boolean deleteUserByUUID(String uuid) {
        Optional<UserDO> userDOOptional = userRepository.findById(uuid);
        if (userDOOptional.isPresent()) {
            UserDO userDO = userDOOptional.get();
            userDO.setDeleted(true);
            userRepository.save(userDO);
            return true;
        } else {
            // handle the case when user is not found
            return false;
        }
    }

    @Override
    public UserVO updateUserByUUID(UserVO userVO) {
        Optional<UserDO> userDOOptional = userRepository.findById(userVO.getId());
        if (userDOOptional.isPresent()) {
            UserDO userDO = userDOOptional.get();
            if (userVO.getNickname() != null) {
                userDO.setNickname(userVO.getNickname());
            }
            if (userVO.getAvatar() != null) {
                userDO.setAvatar(userVO.getAvatar());
            }
            if (userVO.getNickname() != null) {
                userDO.setNickname(userVO.getNickname());
            }
            if (userVO.getDeleted() != null) {
                userDO.setDeleted(userVO.getDeleted());
            }
            userDO.setUpdateTime(LocalDateTime.now());
            userDO.setUpdater(userVO.getUpdater() == null ? UserTypeEnum.ADMIN.getType() : UserTypeEnum.USER.getType());
            return convertToVO(userRepository.save(userDO));
        } else {
            return null;
        }
    }

    private UserVO convertToVO(UserDO userDO) {
        UserVO userVO = new UserVO();
        userVO.setId(userDO.getId());
        userVO.setNickname(userDO.getNickname());
        userVO.setType(userDO.getType());
        userVO.setCreator(userDO.getCreator());
        userVO.setCreateTime(userDO.getCreateTime());
        userVO.setUpdateTime(userDO.getUpdateTime());
        userVO.setUpdater(userDO.getUpdater());
        userVO.setDeleted(userDO.getDeleted());
        return userVO;
    }
}
