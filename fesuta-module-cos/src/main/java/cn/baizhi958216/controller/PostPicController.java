package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.CosService;
import cn.baizhi958216.viewobject.CommentVO;
import cn.baizhi958216.viewobject.PicVO;
import cn.baizhi958216.viewobject.UserWithPostVO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

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
@RequestMapping("/api/v1/fesuta/cos")
public class PostPicController {
    private final CosService cosService;

    public PostPicController(CosService cosService) {
        this.cosService = cosService;
    }

    @PostMapping("/postpic")
    public PicVO postPic(@RequestBody PicVO picVO) {
        return cosService.postPic(picVO);
    }

    @GetMapping("/getPostByUID")
    public PicVO[] getPostByUID(@RequestParam String UID) {
        return cosService.getPostsByUID(UID);
    }

    @GetMapping("/getAllAuthors")
    public UserWithPostVO[] getAllAuthors() {
        return cosService.getAllAuthors();
    }

    @GetMapping("/getRecommendPosts")
    public PicVO[] getRecommendPosts() {
        return cosService.getRecommendPosts();
    }

    @GetMapping("/getBanner")
    public PicVO[] getBannerPosts() {
        return cosService.getBannerPosts();
    }

    @GetMapping("/getCosById")
    public PicVO getCosById(@RequestParam String id) {
        return cosService.getPicById(id);
    }

    @PostMapping("/sendComment")
    public Boolean sendComment(@RequestBody CommentVO commentVO) {
        return cosService.sendComment(commentVO);
    }

    @GetMapping("/getComments")
    public CommentVO[] getComments(@RequestParam String id) {
        return cosService.getComments(id);
    }

    @GetMapping("/likeCount")
    public Boolean likeCount(@RequestParam String id) {
        return cosService.likeCount(id);
    }

    @GetMapping("/shareCount")
    public Boolean shareCount(@RequestParam String id) {
        return cosService.shareCount(id);
    }

    @GetMapping("/remove")
    public Boolean removePic(@RequestParam String id) {
        return cosService.deletePic(id);
    }

}
