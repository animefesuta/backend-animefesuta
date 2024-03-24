package cn.baizhi958216.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.PicDO;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.repository.PostPicRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.CosService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.PicVO;
import cn.baizhi958216.viewobject.UserWithPostVO;

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
        picDO.setBanner(true);
        picDO.setRecommend(true);
        picRepository.save(picDO);
        return coverToVO(picDO);
    }

    @Override
    public PicVO[] getPostsByUID(String UID) {
        return picRepository.findAllByCreator(UID).stream().map(this::coverToVO).toArray(PicVO[]::new);
    }

    @Override
    public UserWithPostVO[] getAllAuthors() {
        return picRepository.findAll()
                .stream()
                .map(p -> {
                    UserWithPostVO userWithPostVO = new UserWithPostVO();
                    userWithPostVO.setUid(p.getCreator());
                    UserDO user = userRepository.findByUid(p.getCreator()).orElse(null);
                    if (user != null) {
                        userWithPostVO.setUsername(user.getNickname());
                        userWithPostVO.setAvatar(user.getAvatar());
                    }
                    return userWithPostVO;
                })
                .distinct()
                .toArray(UserWithPostVO[]::new);
    }

    @Override
    public PicVO[] getRecommendPosts() {
        return picRepository.findAll()
                .stream().filter(p -> p.getRecommend() == true)
                .map(this::coverToVO)
                .toArray(PicVO[]::new);
    }

    @Override
    public PicVO[] getBannerPosts() {
        return picRepository.findAll()
                .stream().filter(p -> p.getBanner() == true)
                .map(this::coverToVO)
                .toArray(PicVO[]::new);
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
        picVO.setAuthorUID(picDO.getCreator());
        picVO.setRecommend(picDO.getRecommend());
        return picVO;
    }

}
