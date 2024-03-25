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
import cn.baizhi958216.viewobject.ForumRankVO;

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
        forumPostDO.setImg(forumPostVO.getImg());
        forumRepository.save(forumPostDO);
        return convertToPostVO(forumPostDO);
    }

    @Override
    public ForumPostVO[] getPostsByTheme(String theme) {
        ForumPostDO forumPostDO[] = forumRepository.findAllByTheme(theme);
        ForumPostVO forumPostVO[] = new ForumPostVO[forumPostDO.length];
        for (int i = 0; i < forumPostDO.length; i++) {
            UserDO userDO = userRepository.findByUid(forumPostDO[i].getCreator()).orElse(null);
            forumPostVO[i] = convertToPostVO(forumPostDO[i]);
            forumPostVO[i].setNickname(userDO.getNickname());
        }
        return forumPostVO;
    }

    @Override
    public ForumPostVO[] getPostsRecommend() {
        ForumPostDO forumPostDO[] = forumRepository.findAllByRecommend(true);
        ForumPostVO forumPostVO[] = new ForumPostVO[forumPostDO.length];
        for (int i = 0; i < forumPostDO.length; i++) {
            UserDO userDO = userRepository.findByUid(forumPostDO[i].getCreator()).orElse(null);
            forumPostVO[i] = convertToPostVO(forumPostDO[i]);
            forumPostVO[i].setNickname(userDO.getNickname());
        }
        return forumPostVO;
    }

    @Override
    public ForumRankVO[] getRanking() {
        ForumPostDO forumPostDO[] = forumRepository.findAllByClickCount(10);
        ForumRankVO forumRankVO[] = new ForumRankVO[forumPostDO.length];
        for (int i = 0; i < forumPostDO.length; i++) {
            forumRankVO[i] = new ForumRankVO();
            forumRankVO[i].setId(forumPostDO[i].getId());
            forumRankVO[i].setTitle(forumPostDO[i].getTitle());
        }
        return forumRankVO;
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
        forumPostVO.setImg(forumPostDO.getImg());
        return forumPostVO;
    }
}