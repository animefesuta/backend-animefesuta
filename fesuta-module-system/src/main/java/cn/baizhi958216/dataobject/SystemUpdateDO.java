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
@Table(name = "fesuta_system_update")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SystemUpdateDO extends BaseDO {
    @Id
    private String id;
    @Column(name = "update_title")
    private String title;
    @Column(name = "update_content", columnDefinition = "TEXT")
    private String content;
}
