package cn.baizhi958216.dataobject;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseDO implements Serializable {
    /**
     * 创建日期
     */
    private LocalDateTime createTime;
    /**
     * 更新日期
     */
    private LocalDateTime updateTime;
    /**
     * 创建者
     */
    private Integer creator;
    /**
     * 更新者
     */
    private Integer updater;
    /**
     * 逻辑删除
     */
    private Boolean deleted;
}
