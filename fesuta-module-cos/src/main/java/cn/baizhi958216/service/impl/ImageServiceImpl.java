package cn.baizhi958216.service.impl;

import java.util.ArrayList;

import cn.baizhi958216.service.ImageService;
import cn.baizhi958216.viewobject.ImageVO;

public class ImageServiceImpl implements ImageService {

    @Override
    public ImageVO uploadImage(ImageVO imageVO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadImage'");
    }

    @Override
    public Boolean deleteImage(String image_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteImage'");
    }

    @Override
    public ImageVO getImage(String image_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }

    @Override
    public ImageVO updateImage(String image_id, ImageVO imageVO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateImage'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByUploader(String image_uploader) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByUploader'");
    }

    @Override
    public ArrayList<ImageVO> findAllImages() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllImages'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByTitleFuzzy(String image_title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByTitleFuzzy'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByImageKeywords(String image_keywords) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByImageKeywords'");
    }

    @Override
    public ArrayList<ImageVO> findImagesByImageCategory(String image_category) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findImagesByImageCategory'");
    }

}
