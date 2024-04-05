package cn.baizhi958216.service.Impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.LiveDO;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.repository.LiveRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.LiveService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.LiveVO;

@Service
public class LiveServiceImpl implements LiveService {
    private final LiveRepository liveRepository;
    private final UserRepository userRepository;

    LiveServiceImpl(LiveRepository liveRepository, UserRepository userRepository) {
        this.liveRepository = liveRepository;
        this.userRepository = userRepository;
    }

    @Override
    public LiveVO createLive(LiveDO liveDO) {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        liveDO.setId(UUID.randomUUID().toString());
        liveDO.setUrl(liveDO.getUrl());
        liveDO.setKey(liveDO.getKey());
        liveDO.setCover(liveDO.getCover());
        liveDO.setRoomId(liveDO.getRoomId());
        liveDO.setTitle(liveDO.getTitle());
        liveDO.setCategory(liveDO.getCategory());
        liveDO.setOnline(0);
        liveDO.setCreator(user.getUid());
        liveDO.setDeleted(false);
        liveDO.setIsLiving(true);
        liveDO.setCreateTime(LocalDateTime.now());
        LiveDO savedDO = this.liveRepository.save(liveDO);
        LiveVO liveVO = new LiveVO();
        liveVO.setKey(savedDO.getKey());
        liveVO.setUrl(savedDO.getUrl());
        return liveVO;
    }

    @Override
    public LiveVO getLiveRoom() {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        LiveDO liveDO = this.liveRepository.findByCreatorAndDeleteIsFalse(user.getUid());
        if (liveDO == null) {
            return null;
        }
        LiveVO liveVO = new LiveVO();
        liveVO.setKey(liveDO.getKey());
        liveVO.setUrl(liveDO.getUrl());
        liveVO.setCover(liveDO.getCover());
        liveVO.setTitle(liveDO.getTitle());
        liveVO.setCategory(liveDO.getCategory());
        return liveVO;
    }

    @Override
    public LiveVO updateLive(LiveVO liveVO) {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        LiveDO liveDO = this.liveRepository.findByCreatorAndDeleteIsFalse(user.getUid());
        if (liveDO == null) {
            return null;
        }
        liveDO.setCover(liveVO.getCover());
        liveDO.setTitle(liveVO.getTitle());
        liveDO.setCategory(liveVO.getCategory());
        liveDO.setUpdateTime(LocalDateTime.now());
        LiveDO savedDO = this.liveRepository.save(liveDO);
        LiveVO liveVO2 = new LiveVO();
        liveVO2.setCategory(savedDO.getCategory());
        liveVO2.setTitle(savedDO.getTitle());
        liveVO2.setCover(savedDO.getCover());
        return liveVO2;
    }

    @Override
    public Boolean closeLive() {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        LiveDO liveDO = this.liveRepository.findByCreatorAndDeleteIsFalse(user.getUid());
        if (liveDO == null) {
            return null;
        }
        liveDO.setDeleted(true);
        liveDO.setUpdateTime(LocalDateTime.now());
        this.liveRepository.save(liveDO);
        return true;
    }

    @Override
    public String getLatestRoom() {
        LiveDO liveDO = this.liveRepository.findLatestRoom();
        if (liveDO == null) {
            return null;
        }
        return liveDO.getRoomId();
    }

    @Override
    public String getRoomStream(String uid) {
        LiveDO liveDO = this.liveRepository.findByCreatorAndDeleteIsFalse(uid);
        if (liveDO == null) {
            return "";
        }
        return liveDO.getRoomId();
    }

    @Override
    public LiveVO[] getAllLivingRoom() {
        LiveDO[] liveDOs = this.liveRepository.findAllLivingRoom();
        LiveVO[] liveVOs = new LiveVO[liveDOs.length];
        for (int i = 0; i < liveDOs.length; i++) {
            liveVOs[i] = new LiveVO();
            liveVOs[i].setCover(liveDOs[i].getCover());
            liveVOs[i].setTitle(liveDOs[i].getTitle());
            UserDO userDO = userRepository.findByUid(liveDOs[i].getCreator()).orElse(null);
            liveVOs[i].setCreator(userDO.getNickname());
            liveVOs[i].setKey(liveDOs[i].getKey());
        }
        return liveVOs;
    }
}
