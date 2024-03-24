package cn.baizhi958216.service.Impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.ForumPostDO;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.repository.ForumRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.ForumService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.ForumPostVO;

@Service
public class ForumServiceImpl implements ForumService {
    private final ForumRepository forumRepository;
    private final UserRepository userRepository;

    public ForumServiceImpl(ForumRepository forumRepository, UserRepository userRepository) {
        this.forumRepository = forumRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ForumPostVO createPost(ForumPostVO forumPostVO) {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        ForumPostDO forumPostDO = new ForumPostDO();
        forumPostDO.setId(UUID.randomUUID().toString());
        forumPostDO.setCreator(user.getUid());
        forumPostDO.setTitle(forumPostVO.getTitle());
        forumPostDO.setTheme(forumPostVO.getTheme());
        forumPostDO.setContent(forumPostVO.getContent());
        forumPostDO.setCreateTime(LocalDateTime.now());
        forumPostDO.setShareCount(0);
        forumPostDO.setClickCount(0);
        forumPostDO.setLikeCount(0);
        forumPostDO.setStatus(false);
        forumPostDO.setStatus_desc("");
        forumPostDO.setRecommend(false);
        forumRepository.save(forumPostDO);
        return convertToPostVO(forumPostDO);
    }

    @Override
    public ForumPostVO[] getPostsByTheme(String theme) {
        ForumPostDO forumPostDO[] = forumRepository.findAllByTheme(theme);
        ForumPostVO forumPostVO[] = new ForumPostVO[forumPostDO.length];
        for (int i = 0; i < forumPostDO.length; i++) {
            forumPostVO[i] = convertToPostVO(forumPostDO[i]);
        }
        return forumPostVO;
    }

    @Override
    public ForumPostVO[] getPostsRecommend() {
        ForumPostDO forumPostDO[] = forumRepository.findAllByRecommend(true);
        ForumPostVO forumPostVO[] = new ForumPostVO[forumPostDO.length];
        for (int i = 0; i < forumPostDO.length; i++) {
            forumPostVO[i] = convertToPostVO(forumPostDO[i]);
        }
        return forumPostVO;
    }

    private ForumPostVO convertToPostVO(ForumPostDO forumPostDO) {
        ForumPostVO forumPostVO = new ForumPostVO();
        forumPostVO.setId(forumPostDO.getId());
        forumPostVO.setCreator(forumPostDO.getCreator());
        forumPostVO.setTitle(forumPostDO.getTitle());
        forumPostVO.setTheme(forumPostDO.getTheme());
        forumPostVO.setContent(forumPostDO.getContent());
        forumPostVO.setCreateTime(forumPostDO.getCreateTime());
        forumPostVO.setLikeCount(forumPostDO.getLikeCount());
        forumPostVO.setShareCount(forumPostDO.getShareCount());
        forumPostVO.setClickCount(forumPostDO.getClickCount());
        return forumPostVO;
    }
}
