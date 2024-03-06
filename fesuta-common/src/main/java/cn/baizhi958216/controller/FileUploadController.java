package cn.baizhi958216.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.dataobject.FileDO;
import cn.baizhi958216.repository.FileRepository;
import cn.baizhi958216.service.FileService;
import cn.baizhi958216.viewobject.FileVO;

import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/common")
public class FileUploadController {

    private final FileService fileService;
    private final FileRepository fileRepository;

    public FileUploadController(FileService fileService, FileRepository fileRepository) {
        this.fileService = fileService;
        this.fileRepository = fileRepository;
    }

    @PostMapping("/fileupload")
    public FileVO[] fileUpload(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(nullableFile -> fileService.store(new FileDO(), new FileVO(), nullableFile, fileRepository))
                .toArray(FileVO[]::new);
    }

    @PostMapping("/fileuploadsingle")
    public FileVO fileUploadSingle(@RequestParam("file") MultipartFile file) {
        return fileService.store(new FileDO(), new FileVO(), file, fileRepository);
    }
}