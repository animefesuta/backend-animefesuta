package cn.baizhi958216.viewobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentVO extends BaseVO {
    private String id;
    private String typeID;
    private String themeID;
    private String parentID;
    private String commentContext;
    private String commentLike;
    private String commentUser;
    private String commentUserNickName;
    private String user_image;
}
