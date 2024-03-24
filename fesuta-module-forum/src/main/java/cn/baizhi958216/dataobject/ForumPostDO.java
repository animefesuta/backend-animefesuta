package cn.baizhi958216.dataobject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fesuta_forum_post")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ForumPostDO extends BaseDO {
    @Id
    /**
     * 帖子id
     */
    private String id;
    @Column(name = "post_forum_title")
    private String title;
    @Column(name = "post_forum_theme")
    private String theme;
    @Column(name = "post_forum_content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "post_forum_clickcount")
    private Integer clickCount;
    @Column(name = "post_forum_likecount")
    private Integer likeCount;
    @Column(name = "post_forum_sharecount")
    private Integer shareCount;
    // 帖子状态
    @Column(name = "post_forum_status")
    private Boolean status;
    // 状态描述
    @Column(name = "post_forum_status_desc")
    private String status_desc;
    // 是否推荐
    @Column(name = "post_forum_recommend")
    private Boolean recommend;
    // 帖子首页图
    @Column(name = "post_forum_img")
    private String img;
}
