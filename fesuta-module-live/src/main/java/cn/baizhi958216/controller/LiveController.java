package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.dataobject.LiveDO;
import cn.baizhi958216.service.LiveService;
import cn.baizhi958216.viewobject.LiveVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@ResponseResultBody
@RequestMapping("/api/v1/fesuta/live")
public class LiveController {
    private final LiveService liveService;

    public LiveController(LiveService liveService) {
        this.liveService = liveService;
    }

    @PostMapping("/createRoom")
    public LiveVO postMethodName(@RequestBody LiveDO liveDO) {
        return liveService.createLive(liveDO);
    }

    @GetMapping("/getLiveRoom")
    public LiveVO getLiveRoom() {
        return liveService.getLiveRoom();
    }

    // 没有新建直播间，使用VO
    @PostMapping("/updateStream")
    public LiveVO updateStream(@RequestBody LiveVO liveVO) {
        return liveService.updateLive(liveVO);
    }

    @GetMapping("/closeStream")
    public Boolean closeStream() {
        return liveService.closeLive();
    }

    @GetMapping("/latestRoom")
    public String getLatestRoom() {
        return this.liveService.getLatestRoom();
    }

    @GetMapping("/getRoomStream/{uid}")
    public String getMethodName(@PathVariable("uid") String uid) {
        return this.liveService.getRoomStream(uid);
    }

}
