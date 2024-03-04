package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.core.ResponseResultBody;
import cn.baizhi958216.service.ImageService;
import cn.baizhi958216.viewobject.FileVO;
import cn.baizhi958216.viewobject.ImageVO;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 需求：
 * 
 * 用户 上传 下载 公开 删除 分享
 * Coser 上传 下载 公开 删除 分享
 * 管理员 上传 下载 公开 删除
 */

@RestController
@ResponseResultBody
@RequestMapping("/api/v1/fesuta/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/imageupload")
    public ImageVO[] imageUpload(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(imageService::uploadImage)
                .toArray(ImageVO[]::new);
    }

    @PostMapping("/imageuploadsingle")
    public ImageVO imageUploadSingle(@RequestParam("file") MultipartFile file) {
        return imageService.uploadImage(file);
    }

}
