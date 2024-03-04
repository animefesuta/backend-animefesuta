package cn.baizhi958216.service;

import java.util.ArrayList;

import cn.baizhi958216.viewobject.ImageVO;

public interface ImageService {
    ImageVO uploadImage(ImageVO imageVO);

    Boolean deleteImage(String image_id);

    ImageVO getImage(String image_id);

    ImageVO updateImage(String image_id, ImageVO imageVO);

    ArrayList<ImageVO> findImagesByUploader(String image_uploader);

    ArrayList<ImageVO> findAllImages();

    /**
     * 模糊查询图片
     * 
     * @param image_title
     * @return ArrayList<ImageVO>
     */
    ArrayList<ImageVO> findImagesByTitleFuzzy(String image_title);

    /**
     * 关键字模糊查询图片
     * 
     * @param image_keywords
     * @return
     */
    ArrayList<ImageVO> findImagesByImageKeywords(String image_keywords);

    /**
     * 分类模糊查询图片
     * 
     * @param image_category
     * @return
     */
    ArrayList<ImageVO> findImagesByImageCategory(String image_category);
}
