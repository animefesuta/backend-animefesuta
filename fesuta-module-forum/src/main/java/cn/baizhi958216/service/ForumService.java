package cn.baizhi958216.service;

import cn.baizhi958216.viewobject.CommentVO;
import cn.baizhi958216.viewobject.ForumPostVO;
import cn.baizhi958216.viewobject.ForumRankVO;

public interface ForumService {
    ForumPostVO createPost(ForumPostVO forumPostVO);

    ForumPostVO[] getPostsByTheme(String theme);

    ForumPostVO getPostById(String id);

    ForumPostVO[] getPostsRecommend();

    ForumRankVO[] getRanking();

    Boolean sendComment(CommentVO commentVO);

    CommentVO[] getComments(String id);
}
