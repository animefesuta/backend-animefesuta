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
}
