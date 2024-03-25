package cn.baizhi958216.service;

import cn.baizhi958216.viewobject.ForumPostVO;
import cn.baizhi958216.viewobject.ForumRankVO;

public interface ForumService {
    ForumPostVO createPost(ForumPostVO forumPostVO);

    ForumPostVO[] getPostsByTheme(String theme);

    ForumPostVO[] getPostsRecommend();

    ForumRankVO[] getRanking();
}
