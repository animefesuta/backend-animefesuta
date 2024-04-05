package cn.baizhi958216.service;

import cn.baizhi958216.dataobject.LiveDO;
import cn.baizhi958216.viewobject.LiveVO;

public interface LiveService {
    LiveVO createLive(LiveDO liveDO);

    LiveVO getLiveRoom();

    LiveVO updateLive(LiveVO liveVO);

    Boolean closeLive();

    String getLatestRoom();

    String getRoomStream(String uid);

    LiveVO[] getAllLivingRoom();
}
