package cn.baizhi958216.service.impl;

import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.enums.UserTypeEnum;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.UserService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserVO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public UserVO createUser(UserVO userVO) {
        UserDO userDO = new UserDO();
        userDO.setNickname(userVO.getNickname());
        userDO.setPassword(userVO.getPassword());
        userDO.setEmail(userVO.getEmail());
        userDO.setType(UserTypeEnum.USER.getType());
        userDO.setCreator(userVO.getEmail());
        userDO.setCreateTime(LocalDateTime.now());
        userDO.setUpdateTime(LocalDateTime.now());
        userDO.setInstruction("这个人很懒，什么都没有留下");
        userDO = userRepository.save(userDO);
        return convertToVO(userDO);
    }

    @Override
    public UserVO findUserByUUID(String uuid) {
        if (uuid == null) {
            return null;
        }
        return userRepository.findById(uuid).map(this::convertToVO).orElse(null);
    }

    @Override
    public List<UserVO> findUserByNickName(String nickname) {
        return userRepository.findAllByNickname(nickname).stream().map(this::convertToVO).collect(Collectors.toList());
    }

    @Override
    public List<UserVO> findUserByNickNameFuzzy(String nickname) {
        return userRepository.findAllByNicknameLike("%" + nickname + "%").stream().map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteUserByUUID(String uuid) {
        if (uuid == null) {
            return false;
        }
        return userRepository.findById(uuid)
                .map(userDO -> {
                    userDO.setDeleted(true);
                    userRepository.save(userDO);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserVO updateUserByUUID(UserVO userVO) {
        String uuid = userVO.getId();
        if (uuid == null) {
            return null;
        }
        String useremail = BaseUserInfo.get("username");
        return userRepository.findById(uuid)
                .map(userDO -> {
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
                    if (userVO.getEmail() != null) {
                        userDO.setEmail(userVO.getEmail());
                    }
                    if (userVO.getInstruction() != null) {
                        userDO.setInstruction(userVO.getInstruction());
                    }
                    if (userVO.getBackgroundImage() != null) {
                        userDO.setBackgroundImage(userVO.getBackgroundImage());
                    }
                    userDO.setUpdateTime(LocalDateTime.now());
                    userDO.setUpdater(useremail);
                    return convertToVO(userRepository.save(userDO));
                })
                .orElse(null);
    }

    @Override
    public UserVO findUserByEmail(String useremail) {
        return userRepository.findByEmail(useremail).map(this::convertToVO).orElse(null);
    }

    @Override
    public UserVO updateUserAvatar(String link) {
        String userEmail = BaseUserInfo.get("username");
        UserDO userDO = userRepository.findByEmail(userEmail).orElse(null);
        if (userDO != null) {
            userDO.setAvatar(link);
            userDO.setUpdateTime(LocalDateTime.now());
            return convertToVO(userRepository.save(userDO));
        }
        return null;
    }

    @Override
    public UserVO updateUserInstruction(String instruction) {
        String userEmail = BaseUserInfo.get("username");
        UserDO userDO = userRepository.findByEmail(userEmail).orElse(null);
        if (userDO != null) {
            userDO.setInstruction(instruction);
            userDO.setUpdateTime(LocalDateTime.now());
            return convertToVO(userRepository.save(userDO));
        }
        return null;
    }

    @Override
    public UserVO updateUserNickName(String nickname) {
        String userEmail = BaseUserInfo.get("username");
        UserDO userDO = userRepository.findByEmail(userEmail).orElse(null);
        if (userDO != null) {
            userDO.setNickname(nickname);
            userDO.setUpdateTime(LocalDateTime.now());
            return convertToVO(userRepository.save(userDO));
        }
        return null;
    }

    @Override
    public UserVO updateUserBackground(String background) {
        String userEmail = BaseUserInfo.get("username");
        UserDO userDO = userRepository.findByEmail(userEmail).orElse(null);
        if (userDO != null) {
            userDO.setBackgroundImage(background);
            userDO.setUpdateTime(LocalDateTime.now());
            return convertToVO(userRepository.save(userDO));
        }
        return null;
    }

    private UserVO convertToVO(UserDO userDO) {
        UserVO userVO = new UserVO();
        userVO.setId(userDO.getId());
        userVO.setAvatar(userDO.getAvatar());
        userVO.setNickname(userDO.getNickname());
        userVO.setType(userDO.getType());
        userVO.setCreator(userDO.getCreator());
        userVO.setEmail(userDO.getEmail());
        userVO.setInstruction(userDO.getInstruction());
        userVO.setCreateTime(userDO.getCreateTime());
        userVO.setUpdateTime(userDO.getUpdateTime());
        userVO.setUpdater(userDO.getUpdater());
        userVO.setDeleted(userDO.getDeleted());
        userVO.setBackgroundImage(userDO.getBackgroundImage());
        return userVO;
    }
}
