package cn.baizhi958216.service.Impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.CommentDO;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.dataobjects.AiPostDO;
import cn.baizhi958216.repository.AiPostRepository;
import cn.baizhi958216.repository.CommentRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.AiService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.AiPostVO;
import cn.baizhi958216.viewobject.CommentVO;

@Service
public class AiServiceImpl implements AiService {

    private final AiPostRepository aiPostRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    AiServiceImpl(AiPostRepository aiPostRepository, UserRepository userRepository,
            CommentRepository commentRepository) {
        this.aiPostRepository = aiPostRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
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
        aiPostDO.setDeleted(false);
        aiPostDO.setStatus(true);
        aiPostRepository.save(aiPostDO);
        return coverToVO(aiPostDO);
    }

    @Override
    public AiPostVO getPicById(String id) {
        AiPostDO aiPostDO = this.aiPostRepository.findById(id).orElse(null);
        if (aiPostDO != null) {
            aiPostDO.setClickCount(aiPostDO.getClickCount() + 1);
            this.aiPostRepository.save(aiPostDO);
            return coverToVO(aiPostDO);
        }
        return null;
    }

    @Override
    public AiPostVO[] getAiPosts() {
        return aiPostRepository.getAiPostsRandom(6)
                .stream().filter(p -> p.getDeleted().equals(false))
                .map(this::coverToVO)
                .toArray(AiPostVO[]::new);
    }

    @Override
    public Boolean sendComment(CommentVO commentVO) {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        CommentDO cdo = new CommentDO();
        cdo.setId(UUID.randomUUID().toString());
        cdo.setCommentContext(commentVO.getCommentContext());
        cdo.setThemeID(commentVO.getThemeID());
        cdo.setTypeID("3");
        cdo.setCreator(user.getUid());
        cdo.setCreateTime(LocalDateTime.now());
        cdo.setDeleted(false);
        CommentDO result = this.commentRepository.save(cdo);
        return result != null;
    }

    @Override
    public Boolean likeCount(String id) {
        AiPostDO aiPostDO = aiPostRepository.findById(id).orElse(null);
        if (aiPostDO != null) {
            aiPostDO.setLikeCount(aiPostDO.getLikeCount() + 1);
            aiPostRepository.save(aiPostDO);
            return true;
        }
        return false;
    }

    @Override
    public Boolean shareCount(String id) {
        AiPostDO aiPostDO = aiPostRepository.findById(id).orElse(null);
        if (aiPostDO != null) {
            aiPostDO.setShareCount(aiPostDO.getShareCount() + 1);
            aiPostRepository.save(aiPostDO);
            return true;
        }
        return false;
    }

    @Override
    public CommentVO[] getComments(String id) {
        CommentDO[] commentsdo = this.commentRepository.findByThemeId(id);
        CommentVO[] commentVO = new CommentVO[commentsdo.length];
        for (int i = 0; i < commentsdo.length; i++) {
            commentVO[i] = convertToCommentVO(commentsdo[i]);
        }
        return commentVO;
    }

    @Override
    public AiPostVO[] getAllAIPosts() {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        return aiPostRepository.findAll()
                .stream().filter(p -> p.getCreator().equals(user
                        .getUid()) && p.getDeleted().equals(false))
                .map(this::coverToVO)
                .toArray(AiPostVO[]::new);
    }

    @Override
    public Boolean removePic(String id) {
        AiPostDO aiPostDO = this.aiPostRepository.findById(id).orElse(null);
        aiPostDO.setDeleted(true);
        this.aiPostRepository.save(aiPostDO);
        return true;
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
        aiPostVO.setStatus(aiPostDO.getStatus());
        aiPostDO.setAi(true);
        return aiPostVO;
    }

    private CommentVO convertToCommentVO(CommentDO commentDO) {
        CommentVO commentVO = new CommentVO();
        commentVO.setCommentContext(commentDO.getCommentContext());
        commentVO.setCommentLike(commentDO.getCommentLike());
        commentVO.setId(commentDO.getId());
        commentVO.setThemeID(commentDO.getThemeID());
        commentVO.setTypeID(commentDO.getTypeID());
        commentVO.setCreateTime(commentDO.getCreateTime());
        commentVO.setCreator(commentDO.getCreator());
        commentVO.setCommentUser(commentDO.getCreator());
        UserDO userDO = userRepository.findByUid(commentDO.getCreator()).orElse(null);
        if (userDO != null) {
            commentVO.setCommentUserNickName(userDO.getNickname());
            commentVO.setUser_image(userDO.getAvatar());
        }
        return commentVO;
    }
}
