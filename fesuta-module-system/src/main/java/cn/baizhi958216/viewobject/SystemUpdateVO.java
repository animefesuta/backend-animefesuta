package cn.baizhi958216.viewobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SystemUpdateVO extends BaseVO {
    private String id;
    private String title;
    private String content;
}
