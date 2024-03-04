package cn.baizhi958216.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.core.StorageProperties;
import cn.baizhi958216.dataobject.ImageDO;
import cn.baizhi958216.exception.StorageException;
import cn.baizhi958216.repository.ImageRepository;
import cn.baizhi958216.service.FileService;
import cn.baizhi958216.service.ImageService;
import cn.baizhi958216.viewobject.FileVO;
import cn.baizhi958216.viewobject.ImageVO;

@Service
public class ImageServiceImpl implements ImageService {

    private Path rootLocation;
    private final ImageRepository imageRepository;
    private final FileService fileService = new FileServiceImpl(new StorageProperties(), "upload-dir/images");

    public ImageServiceImpl(StorageProperties properties, ImageRepository imageRepository) {
        properties.setLocation("upload-dir/images");
        if (properties.getLocation().trim().length() == 0) {
            throw new StorageException("文件上传路径不可为空！");
        }
        this.rootLocation = Paths.get(properties.getLocation());
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageVO uploadImage(MultipartFile file) {
        ImageVO imageVO = new ImageVO();
        ImageDO imageDO = new ImageDO();
        FileVO fileVO = fileService.store(imageDO, imageVO, file, imageRepository);
        imageVO.setId(fileVO.getId());
        imageVO.setOriginalFileName(fileVO.getOriginalFileName());
        imageVO.setFileName(fileVO.getFileName());
        imageVO.setFileHash(fileVO.getFileHash());
        imageVO.setCreateTime(fileVO.getCreateTime());
        imageVO.setFilePath(fileVO.getFilePath());
        imageVO.setFileSize(fileVO.getFileSize());
        imageVO.setFileType(fileVO.getFileType());
        return imageVO;
    }

    @Override
    public Boolean deleteImage(String imageId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteImage'");
    }

    @Override
    public ImageVO getImage(String imageId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }

    @Override
    public ImageVO updateImage(String imageId, ImageVO imageVO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateImage'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByUploader(String imageUploader) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByUploader'");
    }

    @Override
    public ArrayList<ImageVO> findAllImages() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllImages'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByTitleFuzzy(String imageTitle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByTitleFuzzy'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByImageKeywords(String imageKeywords) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByImageKeywords'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByImageCategory(String imageCategory) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByImageCategory'");
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
