package cn.baizhi958216.dataobject;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fusta_file")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDO extends BaseDO {
    @Id
    /**
     * 文件唯一标识
     */
    private String id;
    /**
     * 文件名：uuid+fileName
     */
    private String fileName;
    /**
     * 原始文件名
     */
    private String originalFileName;
    /**
     * 文件hash
     */
    private String fileHash;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件类型
     */
    private String fileType;
}
