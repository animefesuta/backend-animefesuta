package cn.baizhi958216.dataobject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fesuta_comments")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDO extends BaseDO {
    @Id
    /**
     * 评论唯一标识
     */
    private String id;

    /**
     * 评论类型 返图 帖子 直播间
     */
    @Column(name = "type_id")
    private String typeID;

    /**
     * 评论对应主题ID
     */
    @Column(name = "theme_id")
    private String themeID;

    /**
     * 父评论ID
     */
    @Column(name = "parent_id")
    private String parentID;

    /**
     * 评论内容
     */
    @Column(name = "comment_context", columnDefinition = "TEXT")
    private String commentContext;

    /**
     * 评论点赞数
     */
    @Column(name = "comment_like", columnDefinition = "TEXT")
    private String commentLike;
}
