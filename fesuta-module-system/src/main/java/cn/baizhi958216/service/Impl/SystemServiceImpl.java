package cn.baizhi958216.service.Impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.baizhi958216.dataobject.SystemUpdateDO;
import cn.baizhi958216.dataobject.UserDO;
import cn.baizhi958216.repository.SystemUpdateRepository;
import cn.baizhi958216.repository.UserRepository;
import cn.baizhi958216.service.SystemService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.viewobject.SystemUpdateVO;

@Service
public class SystemServiceImpl implements SystemService {
    private final SystemUpdateRepository systemUpdateRepository;
    private final UserRepository userRepository;

    SystemServiceImpl(SystemUpdateRepository systemUpdateRepository, UserRepository userRepository) {
        this.systemUpdateRepository = systemUpdateRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String getLatestUpdate() {
        return this.systemUpdateRepository.findLatest().getTitle();
    }

    @Override
    public SystemUpdateVO createSystemUpdateInfo(SystemUpdateDO systemUpdateDO) {
        String useremail = BaseUserInfo.get("username");
        UserDO user = userRepository.findByEmail(useremail).orElse(null);
        if (user == null) {
            return null;
        }
        systemUpdateDO.setId(UUID.randomUUID().toString());
        systemUpdateDO.setCreator(user.getUid());
        systemUpdateDO.setDeleted(false);
        systemUpdateDO.setCreateTime(LocalDateTime.now());
        SystemUpdateDO savedDO = systemUpdateRepository.save(systemUpdateDO);
        return convertToSystemUpdateVO(savedDO);
    }

    private SystemUpdateVO convertToSystemUpdateVO(SystemUpdateDO systemUpdateDO) {
        SystemUpdateVO systemUpdateVO = new SystemUpdateVO();
        systemUpdateVO.setId(systemUpdateDO.getId());
        systemUpdateVO.setTitle(systemUpdateDO.getTitle());
        systemUpdateVO.setContent(systemUpdateDO.getContent());
        systemUpdateVO.setCreator(systemUpdateDO.getCreator());
        systemUpdateVO.setCreateTime(systemUpdateDO.getCreateTime());
        return systemUpdateVO;
    }
}
