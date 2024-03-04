package cn.baizhi958216.dataobject;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fesuta_cos_image")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ImageDO extends FileDO {
    /**
     * 图片的标题。
     */
    @Column(name = "image_title")
    private String imageTitle;

    /**
     * 图片文件的URL。
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 图片的上传者。
     */
    @Column(name = "image_uploader")
    private String imageUploader;

    /**
     * 图片的描述。
     */
    @Column(name = "image_description")
    private String imageDescription;

    /**
     * 图片的关键词。
     */
    @Column(name = "image_keywords")
    private ArrayList<String> imageKeywords;

    /**
     * 图片所属的分类。
     */
    @Column(name = "image_category")
    private ArrayList<String> imageCategory;

    /**
     * 图片的可见性（例如：公开、私有）。
     */
    @Column(name = "image_visibility")
    private String imageVisibility;

    /**
     * 图片的下载次数。
     */
    @Column(name = "image_count_download")
    private Integer imageCountDownload;

    /**
     * 图片的点赞数。
     */
    @Column(name = "image_count_likes")
    private Integer imageCountLikes;

    /**
     * 图片被收藏的次数。
     */
    @Column(name = "image_count_collection")
    private Integer imageCountCollection;

    /**
     * 图片的评论数。
     */
    @Column(name = "image_count_comments")
    private Integer imageCountComments;

    /**
     * 图片的版权信息。
     */
    @Column(name = "image_copyright_information")
    private String imageCopyrightInformation;
}