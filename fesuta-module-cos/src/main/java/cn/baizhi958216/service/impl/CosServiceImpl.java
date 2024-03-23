package cn.baizhi958216.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.PicDO;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.repository.PostPicRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.CosService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.PicVO;

@Service
public class CosServiceImpl implements CosService {

    private final PostPicRepository picRepository;
    private final UserRepository userRepository;

    CosServiceImpl(PostPicRepository picRepository, UserRepository userRepository) {
        this.picRepository = picRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PicVO postPic(PicVO picVO) {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        PicDO picDO = new PicDO();
        picDO.setId(UUID.randomUUID().toString());
        picDO.setImage(picVO.getImage());
        picDO.setTitle(picVO.getTitle());
        picDO.setTheme(picVO.getTheme());
        picDO.setTags(picVO.getTags());
        picDO.setCoser(picVO.getCoser());
        picDO.setCreator(user.getUid());
        picDO.setCreateTime(LocalDateTime.now());
        picDO.setUpdateTime(LocalDateTime.now());
        picRepository.save(picDO);
        return coverToVO(picDO);
    }

    @Override
    public PicVO[] getPostsByUID(String UID) {
        return picRepository.findAllByCreator(UID).stream().map(this::coverToVO).toArray(PicVO[]::new);
    }

    private PicVO coverToVO(PicDO picDO) {
        PicVO picVO = new PicVO();
        picVO.setId(picDO.getId());
        picVO.setImage(picDO.getImage());
        picVO.setTitle(picDO.getTitle());
        picVO.setTheme(picDO.getTheme());
        picVO.setTags(picDO.getTags());
        picVO.setCoser(picDO.getCoser());
        picVO.setCreator(picDO.getCreator());
        picVO.setCreateTime(picDO.getCreateTime());
        picVO.setUpdateTime(picDO.getUpdateTime());
        return picVO;
    }

}
