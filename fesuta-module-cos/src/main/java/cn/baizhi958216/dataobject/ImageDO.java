package cn.baizhi958216.dataobject;

import java.util.ArrayList;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "cos_image")
@Entity
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ImageDO extends BaseDO {
    /**
     * 图片的唯一标识符。
     */
    @Builder.Default
    @Id
    private String image_id = UUID.randomUUID().toString();

    /**
     * 图片的标题。
     */
    private String image_title;

    /**
     * 图片文件的URL。
     */
    private String image_url;

    /**
     * 图片的上传者。
     */
    private String image_uploader;

    /**
     * 图片的描述。
     */
    private String image_description;

    /**
     * 图片的关键词。
     */
    private ArrayList<String> image_keywords;

    /**
     * 图片所属的分类。
     */
    private ArrayList<String> image_category;

    /**
     * 图片的可见性（例如：公开、私有）。
     */
    private String image_visibility;

    /**
     * 图片的下载次数。
     */
    private Integer image_count_download;

    /**
     * 图片的点赞数。
     */
    private Integer image_count_likes;

    /**
     * 图片被收藏的次数。
     */
    private Integer image_count_collection;

    /**
     * 图片的评论数。
     */
    private Integer image_count_comments;

    /**
     * 图片的版权信息。
     */
    private String image_copyright_information;
}