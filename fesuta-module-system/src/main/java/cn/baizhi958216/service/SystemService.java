package cn.baizhi958216.service;

import cn.baizhi958216.dataobject.SystemUpdateDO;
import cn.baizhi958216.viewobject.SystemUpdateVO;

public interface SystemService {
    String getLatestUpdate();

    SystemUpdateVO createSystemUpdateInfo(SystemUpdateDO systemUpdateDO);
}
