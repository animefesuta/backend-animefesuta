package cn.baizhi958216.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.baizhi958216.viewobject.CommentVO;
import cn.baizhi958216.viewobject.PicVO;
import cn.baizhi958216.viewobject.UserWithPostVO;

public interface CosService {
    PicVO postPic(PicVO picVO);

    PicVO[] getPostsByUID(String UID);

    UserWithPostVO[] getAllAuthors();

    PicVO[] getRecommendPosts();

    PicVO[] getBannerPosts();

    PicVO getPicById(String id);

    Boolean sendComment(CommentVO commentVO);

    CommentVO[] getComments(String id);

    Boolean likeCount(String id);

    Boolean shareCount(String id);

    PicVO[] getAllPicPosts();

    Boolean deletePic(String id);
}
