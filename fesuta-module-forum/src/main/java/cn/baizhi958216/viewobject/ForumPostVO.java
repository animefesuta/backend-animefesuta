package cn.baizhi958216.viewobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ForumPostVO extends BaseVO {
    private String id;
    private String title;
    private String theme;
    private String content;
    private Integer clickCount;
    private Integer likeCount;
    private Integer shareCount;
    private Boolean status;
    private String status_desc;
    private Boolean recommend;
}
