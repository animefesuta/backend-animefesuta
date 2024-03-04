package cn.baizhi958216.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.viewobject.ImageVO;

public interface ImageService {
    ImageVO uploadImage(MultipartFile multipartFile);

    Boolean deleteImage(String imageId);

    ImageVO getImage(String imageId);

    ImageVO updateImage(String imageId, ImageVO imageVO);

    ArrayList<ImageVO> findImagesByUploader(String imageUploader);

    ArrayList<ImageVO> findAllImages();

    /**
     * 模糊查询图片
     * 
     * @param image_title
     * @return ArrayList<ImageVO>
     */
    ArrayList<ImageVO> findImagesByTitleFuzzy(String imageTitle);

    /**
     * 关键字模糊查询图片
     * 
     * @param image_keywords
     * @return
     */
    ArrayList<ImageVO> findImagesByImageKeywords(String imageKeywords);

    /**
     * 分类模糊查询图片
     * 
     * @param image_category
     * @return
     */
    ArrayList<ImageVO> findImagesByImageCategory(String imageCategory);

    public void init();
}
