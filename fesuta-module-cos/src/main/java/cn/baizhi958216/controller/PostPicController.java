package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.CosService;
import cn.baizhi958216.service.ImageService;
import cn.baizhi958216.viewobject.ImageVO;
import cn.baizhi958216.viewobject.PicVO;

import java.util.Arrays;

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

}
