package cn.baizhi958216.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.dataobject.ImageDO;
import cn.baizhi958216.repository.ImageRepository;
import cn.baizhi958216.service.FileService;
import cn.baizhi958216.service.ImageService;
import cn.baizhi958216.viewobject.FileVO;
import cn.baizhi958216.viewobject.ImageVO;

@Service
public class ImageServiceImpl implements ImageService {

    private final FileService fileService;
    private final ImageRepository imageRepository;

    ImageServiceImpl(FileService fileService, ImageRepository imageRepository) {
        this.fileService = fileService;
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
        imageVO.setUpdater(fileVO.getCreator());
        return imageVO;
    }

    @Override
    public Boolean deleteImage(String imageId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteImage'");
    }

    @Override
    public ImageVO getImage(String imageId) {
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }

    @Override
    public ImageVO updateImage(String imageId, ImageVO imageVO) {
        throw new UnsupportedOperationException("Unimplemented method 'updateImage'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByUploader(String imageUploader) {
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByUploader'");
    }

    @Override
    public ArrayList<ImageVO> findAllImages() {
        throw new UnsupportedOperationException("Unimplemented method 'findAllImages'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByTitleFuzzy(String imageTitle) {
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByTitleFuzzy'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByImageKeywords(String imageKeywords) {
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByImageKeywords'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByImageCategory(String imageCategory) {
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByImageCategory'");
    }
}
