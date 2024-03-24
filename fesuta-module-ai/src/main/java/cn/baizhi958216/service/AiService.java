package cn.baizhi958216.service;

import cn.baizhi958216.viewobject.AiPostVO;

public interface AiService {
    AiPostVO postPic(AiPostVO aiPostVO);

    AiPostVO[] getAiPosts();
}
