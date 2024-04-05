package cn.baizhi958216.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.CommentDO;
import cn.baizhi958216.dataobject.PicDO;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.repository.CommentRepository;
import cn.baizhi958216.repository.PostPicRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.CosService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.CommentVO;
import cn.baizhi958216.viewobject.PicVO;
import cn.baizhi958216.viewobject.UserWithPostVO;

@Service
public class CosServiceImpl implements CosService {

    private final PostPicRepository picRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    CosServiceImpl(PostPicRepository picRepository, UserRepository userRepository,
            CommentRepository commentRepository) {
        this.picRepository = picRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
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
        picDO.setClickCount(0);
        picDO.setLikeCount(0);
        picDO.setShareCount(0);
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

    @Override
    public PicVO getPicById(String id) {
        PicDO picDO = this.picRepository.findById(id).orElse(null);
        if (picDO != null) {
            picDO.setClickCount(picDO.getClickCount() + 1);
            this.picRepository.save(picDO);
            return coverToVO(picDO);
        }
        return null;
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
            commentVO[i] = convertToCommentVO(commentsdo[i]);
        }
        return commentVO;
    }

    @Override
    public Boolean likeCount(String id) {
        PicDO picDO = picRepository.findById(id).orElse(null);
        if (picDO != null) {
            picDO.setLikeCount(picDO.getLikeCount() + 1);
            picRepository.save(picDO);
            return true;
        }
        return false;
    }

    @Override
    public Boolean shareCount(String id) {
        PicDO picDO = picRepository.findById(id).orElse(null);
        if (picDO != null) {
            picDO.setShareCount(picDO.getShareCount() + 1);
            picRepository.save(picDO);
            return true;
        }
        return false;
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
        picVO.setLikeCount(picDO.getLikeCount());
        picVO.setClickCount(picDO.getClickCount());
        picVO.setShareCount(picDO.getShareCount());
        UserDO userDO = userRepository.findByUid(picDO.getCreator()).orElse(null);
        if (userDO != null) {
            picVO.setNickName(userDO.getNickname());
        }
        return picVO;
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
