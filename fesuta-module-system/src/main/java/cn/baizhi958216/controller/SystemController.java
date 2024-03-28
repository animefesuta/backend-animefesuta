package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.dataobject.SystemUpdateDO;
import cn.baizhi958216.service.SystemService;
import cn.baizhi958216.viewobject.SystemUpdateVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@ResponseResultBody
@RequestMapping("/api/v1/fesuta/system")
public class SystemController {
    private final SystemService systemService;

    SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @PostMapping("/createupdateinfo")
    public SystemUpdateVO createUpdateInfo(@RequestBody SystemUpdateDO systemUpdateDO) {
        return this.systemService.createSystemUpdateInfo(systemUpdateDO);
    }

    @GetMapping("/latestupdate")
    public String getLatestUpdate() {
        return this.systemService.getLatestUpdate();
    }

}
