package cn.baizhi958216.service;

import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.viewobject.FileVO;

public interface FileService {
    public void init();

    FileVO store(MultipartFile file);
}
