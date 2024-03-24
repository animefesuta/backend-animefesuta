package cn.baizhi958216.service.Impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.dataobjects.AiPostDO;
import cn.baizhi958216.repository.AiPostRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.AiService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.AiPostVO;

@Service
public class AiServiceImpl implements AiService {

    private final AiPostRepository aiPostRepository;
    private final UserRepository userRepository;

    AiServiceImpl(AiPostRepository aiPostRepository, UserRepository userRepository) {
        this.aiPostRepository = aiPostRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AiPostVO postPic(AiPostVO aiPostVO) {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        AiPostDO aiPostDO = new AiPostDO();
        aiPostDO.setId(UUID.randomUUID().toString());
        aiPostDO.setImage(aiPostVO.getImage());
        aiPostDO.setTitle(aiPostVO.getTitle());
        aiPostDO.setTheme(aiPostVO.getTheme());
        aiPostDO.setTags(aiPostVO.getTags());
        aiPostDO.setCoser(aiPostVO.getCoser());
        aiPostDO.setCreator(user.getUid());
        aiPostDO.setCreateTime(LocalDateTime.now());
        aiPostDO.setUpdateTime(LocalDateTime.now());
        aiPostDO.setBanner(true);
        aiPostDO.setRecommend(true);
        aiPostDO.setAi(true);
        aiPostRepository.save(aiPostDO);
        return coverToVO(aiPostDO);
    }

    @Override
    public AiPostVO[] getAiPosts() {
        return aiPostRepository.findAll()
                .stream()
                .map(this::coverToVO)
                .toArray(AiPostVO[]::new);
    }

    private AiPostVO coverToVO(AiPostDO aiPostDO) {
        AiPostVO aiPostVO = new AiPostVO();
        aiPostVO.setId(aiPostDO.getId());
        aiPostVO.setImage(aiPostDO.getImage());
        aiPostVO.setTitle(aiPostDO.getTitle());
        aiPostVO.setTheme(aiPostDO.getTheme());
        aiPostVO.setTags(aiPostDO.getTags());
        aiPostVO.setCoser(aiPostDO.getCoser());
        aiPostVO.setCreator(aiPostDO.getCreator());
        aiPostVO.setCreateTime(aiPostDO.getCreateTime());
        aiPostVO.setUpdateTime(aiPostDO.getUpdateTime());
        aiPostVO.setAuthorUID(aiPostDO.getCreator());
        aiPostVO.setRecommend(aiPostDO.getRecommend());
        aiPostDO.setAi(true);
        return aiPostVO;
    }

}
