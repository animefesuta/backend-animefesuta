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
@Table(name = "fesuta_cos_post_forum")
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
    @Column(name = "post_forum_content")
    private String content;
}
