package cn.baizhi958216.service.Impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.checkerframework.checker.units.qual.cd;
import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.CommentDO;
import cn.baizhi958216.dataobject.ForumPostDO;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.repository.CommentRepository;
import cn.baizhi958216.repository.ForumRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.ForumService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.CommentVO;
import cn.baizhi958216.viewobject.ForumPostVO;
import cn.baizhi958216.viewobject.ForumRankVO;

@Service
public class ForumServiceImpl implements ForumService {
    private final ForumRepository forumRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ForumServiceImpl(ForumRepository forumRepository, UserRepository userRepository,
            CommentRepository commentRepository) {
        this.forumRepository = forumRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
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

    @Override
    public ForumPostVO getPostById(String id) {
        ForumPostDO fdo = forumRepository.findById(id).orElse(null);
        UserDO userDO = userRepository.findByUid(fdo.getCreator()).orElse(null);
        ForumPostVO fvo = convertToPostVO(fdo);
        fvo.setNickname(userDO.getNickname());
        return fvo;
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
    public CommentVO[] getComments(String id) {
        CommentDO[] commentsdo = this.commentRepository.findByThemeId(id);
        CommentVO[] commentVO = new CommentVO[commentsdo.length];
        for (int i = 0; i < commentsdo.length; i++) {
            commentVO[i] = convertToPostVO(commentsdo[i]);
        }
        return commentVO;
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

    private CommentVO convertToPostVO(CommentDO commentDO) {
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
