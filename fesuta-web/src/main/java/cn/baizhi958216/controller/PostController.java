package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.AiService;
import cn.baizhi958216.service.CosService;
import cn.baizhi958216.viewobject.AiPostVO;
import cn.baizhi958216.viewobject.PicVO;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/manage")
@ResponseResultBody
public class PostController {
    private AiService aiService;
    private CosService cosService;

    public PostController(AiService aiService, CosService cosService) {
        this.aiService = aiService;
        this.cosService = cosService;
    }

    @GetMapping("/post/getallaiposts")
    public AiPostVO[] GetAllAIPosts() {
        return aiService.getAllAIPosts();
    }

    @GetMapping("/post/getallpicposts")
    public PicVO[] GetAllPicPosts() {
        return cosService.getAllPicPosts();
    }
}
