package cn.baizhi958216.service.impl;

import cn.baizhi958216.core.StorageProperties;
import cn.baizhi958216.dataobject.FileDO;
import cn.baizhi958216.exception.StorageException;
import cn.baizhi958216.service.FileService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.utils.FileMd5HashUtils;
import cn.baizhi958216.viewobject.FileVO;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private final Path rootLocation;

    @Autowired
    public FileServiceImpl(StorageProperties properties) {
        if (properties.getLocation().trim().length() == 0) {
            throw new StorageException("文件上传路径不可为空！");
        }
        this.rootLocation = Paths.get(properties.getLocation());
    }

    public FileServiceImpl(StorageProperties properties, String location) {
        properties.setLocation(location);
        if (properties.getLocation().trim().length() == 0) {
            throw new StorageException("文件上传路径不可为空！");
        }
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public <S extends FileDO, T extends FileVO> T store(S s, T t, MultipartFile file,
            JpaRepository<S, Object> jpaRepository) {
        FileMd5HashUtils fileMd5HashUtils = new FileMd5HashUtils();
        try {
            if (file.isEmpty()) {
                throw new StorageException("上传文件内容为空！");
            }
            String uuid = UUID.randomUUID().toString();
            Path destinationFile = this.rootLocation
                    .resolve(Paths.get(uuid + "_" + file.getOriginalFilename()))
                    .normalize()
                    .toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("无法将文件存储在当前目录之外！");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                String fileHash = fileMd5HashUtils.getFileHash256(inputStream);
                String userEmail = BaseUserInfo.get("username");
                s.setId(uuid);
                s.setCreator(userEmail);
                s.setOriginalFileName(file.getOriginalFilename());
                s.setFileName(uuid + "_" + file.getOriginalFilename());
                s.setFileHash(fileHash);
                s.setCreateTime(LocalDateTime.now());
                s.setFilePath(destinationFile.toString());
                s.setFileSize(file.getSize());
                s.setFileType(file.getContentType());

                jpaRepository.save(s);

                t.setId(s.getId());
                t.setOriginalFileName(file.getOriginalFilename());
                t.setFileName(uuid + "_" + file.getOriginalFilename());
                t.setFileHash(fileHash);
                t.setCreateTime(s.getCreateTime());
                t.setFilePath(destinationFile.toString());
                t.setFileSize(file.getSize());
                t.setFileType(file.getContentType());
            }
        } catch (IOException e) {
            throw new StorageException("文件保存失败！", e);
        }
        return t;
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
