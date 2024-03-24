package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.ForumService;
import cn.baizhi958216.viewobject.ForumPostVO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@ResponseResultBody
@RequestMapping("/api/v1/fesuta/forum")
public class ForumController {
    private final ForumService forumService;

    ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @PostMapping("/createpost")
    public ForumPostVO createPost(@RequestBody ForumPostVO forumPostVO) {
        return forumService.createPost(forumPostVO);
    }

}
