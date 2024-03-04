package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.service.FileService;
import cn.baizhi958216.viewobject.FileVO;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/common")
public class FileUploadController {

    private final FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/fileupload")
    public FileVO[] fileUpload(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(fileService::store)
                .toArray(FileVO[]::new);
    }

    @PostMapping("/fileuploadsingle")
    public FileVO fileUploadSingle(@RequestParam("file") MultipartFile file) {
        return fileService.store(file);
    }
}