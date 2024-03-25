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
@Table(name = "fesuta_live")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class LiveDO extends BaseDO {
    @Id
    private String id;
    // 推流地址
    @Column(name = "live_url")
    private String url;
    // 推流码
    @Column(name = "live_key")
    private String key;
    // 封面图
    @Column(name = "live_cover")
    private String cover;
    // 视频拉流ID
    @Column(name = "live_roomId")
    private String roomId;
    // 直播间标题
    @Column(name = "live_title")
    private String title;
    // 直播间分类
    @Column(name = "live_category")
    private String category;
    // 直播间人数
    @Column(name = "live_online")
    private Integer online;
}
