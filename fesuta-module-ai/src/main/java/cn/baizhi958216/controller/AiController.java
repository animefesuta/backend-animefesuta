package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.AiService;
import cn.baizhi958216.viewobject.AiPostVO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 需求：
 * 
 * 用户 上传 下载 公开 删除 分享
 * Coser 上传 下载 公开 删除 分享
 * 管理员 上传 下载 公开 删除
 */

@CrossOrigin
@RestController
@ResponseResultBody
@RequestMapping("/api/v1/fesuta/ai")
public class AiController {
    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/postpic")
    public AiPostVO postPic(@RequestBody AiPostVO aiPostVO) {
        return aiService.postPic(aiPostVO);
    }

    @GetMapping("/getAiPosts")
    public AiPostVO[] getAiPosts() {
        return aiService.getAiPosts();
    }

}
