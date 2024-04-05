package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.ForumService;
import cn.baizhi958216.viewobject.CommentVO;
import cn.baizhi958216.viewobject.ForumPostVO;
import cn.baizhi958216.viewobject.ForumRankVO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/getPostsByTheme")
    public ForumPostVO[] getPostsByTheme(@RequestParam String theme) {
        if (theme.equals("0")) {
            return forumService.getPostsRecommend();
        }
        return forumService.getPostsByTheme(theme);
    }

    @GetMapping("/getRanking")
    public ForumRankVO[] getRanking() {
        return forumService.getRanking();
    }

    @GetMapping("/getPostById")
    public ForumPostVO getPostById(@RequestParam String id) {
        return forumService.getPostById(id);
    }

    @PostMapping("/sendComment")
    public Boolean sendComment(@RequestBody CommentVO commentVO) {
        return forumService.sendComment(commentVO);
    }

    @GetMapping("/getComments")
    public CommentVO[] getComments(@RequestParam String id) {
        return forumService.getComments(id);
    }

}
