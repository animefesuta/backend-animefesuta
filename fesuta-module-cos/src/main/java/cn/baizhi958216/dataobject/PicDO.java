package cn.baizhi958216.dataobject;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fesuta_cos_post_pic")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PicDO extends BaseDO {
    @Id
    /**
     * 文件唯一标识
     */
    private String id;
    @Column(name = "post_pic_image")
    private ArrayList<String> image;
    @Column(name = "post_pic_title")
    private String title;
    @Column(name = "post_pic_theme")
    private String theme;
    @Column(name = "post_pic_tags")
    private ArrayList<String> tags;
    @Column(name = "post_pic_coser")
    private ArrayList<String> coser;
    // 图片状态
    @Column(name = "post_pic_status")
    private Boolean status;
    // 状态描述
    @Column(name = "post_pic_status_desc")
    private String status_desc;
    // 是否推荐
    @Column(name = "post_pic_recommend")
    private Boolean recommend;
    // 是否横幅
    @Column(name = "post_pic_banner")
    private Boolean banner;

    @Column(name = "post_pic_likecount")
    private int likeCount;
    @Column(name = "post_pic_sharecount")
    private int shareCount;
    @Column(name = "post_pic_clickcount")
    private int clickCount;
}
