package cn.baizhi958216.service.impl;

import cn.baizhi958216.core.StorageProperties;
import cn.baizhi958216.exception.StorageException;
import cn.baizhi958216.service.FileService;
import cn.baizhi958216.viewobject.FileVO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private final Path rootLocation;

    public FileServiceImpl(StorageProperties properties) {

        if (properties.getLocation().trim().length() == 0) {
            throw new StorageException("文件上传路径不可为空！");
        }

        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public FileVO store(MultipartFile file) {
        FileVO fileVO = new FileVO();
        try {
            if (file.isEmpty()) {
                throw new StorageException("上传文件内容为空！");
            }
            Path destinationFile = this.rootLocation
                    .resolve(Paths.get(file.getOriginalFilename()))
                    .normalize()
                    .toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("无法将文件存储在当前目录之外！");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                fileVO.setFileName(file.getOriginalFilename());
                fileVO.setCreateTime(LocalDateTime.now());
                fileVO.setFilePath(destinationFile.toString());
                fileVO.setFileSize(file.getSize());
                fileVO.setFileType(file.getContentType());
            }
        } catch (IOException e) {
            throw new StorageException("文件保存失败！", e);
        }
        return fileVO;
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("初始化存储路径失败！", e);
        }
    }
}
