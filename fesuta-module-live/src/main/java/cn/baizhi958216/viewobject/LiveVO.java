package cn.baizhi958216.viewobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LiveVO extends BaseVO {
    // 推流码
    private String key;
    // 推流地址
    private String url;
    private String cover;
    private String title;
    private String category;
}
