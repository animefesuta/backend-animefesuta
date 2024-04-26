package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.AiService;
import cn.baizhi958216.viewobject.AiPostVO;
import cn.baizhi958216.viewobject.CommentVO;
import cn.baizhi958216.viewobject.PicVO;

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

    @GetMapping("/getCosById")
    public AiPostVO getCosById(@RequestParam String id) {
        return aiService.getPicById(id);
    }

    @GetMapping("/getComments")
    public CommentVO[] getComments(@RequestParam String id) {
        return aiService.getComments(id);
    }

    @PostMapping("/sendComment")
    public Boolean sendComment(@RequestBody CommentVO commentVO) {
        return aiService.sendComment(commentVO);
    }

    @GetMapping("/likeCount")
    public Boolean likeCount(@RequestParam String id) {
        return aiService.likeCount(id);
    }

    @GetMapping("/shareCount")
    public Boolean shareCount(@RequestParam String id) {
        return aiService.shareCount(id);
    }

    @GetMapping("/remove")
    public Boolean removePic(@RequestParam String id) {
        return aiService.removePic(id);
    }

}
