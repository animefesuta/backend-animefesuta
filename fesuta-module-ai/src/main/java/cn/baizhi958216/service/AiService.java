package cn.baizhi958216.service;

import cn.baizhi958216.viewobject.AiPostVO;
import cn.baizhi958216.viewobject.CommentVO;

public interface AiService {
    AiPostVO postPic(AiPostVO aiPostVO);

    AiPostVO[] getAiPosts();

    AiPostVO getPicById(String id);

    CommentVO[] getComments(String id);

    Boolean sendComment(CommentVO commentVO);

    Boolean likeCount(String id);

    Boolean shareCount(String id);

    AiPostVO[] getAllAIPosts();

    Boolean removePic(String id);
}
