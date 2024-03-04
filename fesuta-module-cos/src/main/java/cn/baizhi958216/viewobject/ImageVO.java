package cn.baizhi958216.viewobject;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ImageVO extends FileVO {
    /**
     * 图片的描述。
     */
    private String imageDescription;

    /**
     * 图片的关键词。
     */
    private ArrayList<String> imageKeywords;

    /**
     * 图片所属的分类。
     */
    private ArrayList<String> imageCategory;

    /**
     * 图片的可见性（例如：公开、私有）。
     */
    private String imageVisibility;

    /**
     * 图片的下载次数。
     */
    private Integer imageCountDownload;

    /**
     * 图片的点赞数。
     */
    private Integer imageCountLikes;

    /**
     * 图片被收藏的次数。
     */
    private Integer imageCountCollection;

    /**
     * 图片的评论数。
     */
    private Integer imageCountComments;

    /**
     * 图片的版权信息。
     */
    private String imageCopyrightInformation;
}