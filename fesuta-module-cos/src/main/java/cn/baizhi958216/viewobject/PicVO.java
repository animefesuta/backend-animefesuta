package cn.baizhi958216.viewobject;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PicVO extends BaseVO {
    private String id;
    private ArrayList<String> image;
    private String title;
    private String theme;
    private ArrayList<String> tags;
    private ArrayList<String> coser;
    private String authorUID;
    private Boolean status;
    private String status_desc;
    private Boolean recommend;
    private Boolean banner;
}
