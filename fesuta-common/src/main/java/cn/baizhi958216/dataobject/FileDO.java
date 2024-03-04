package cn.baizhi958216.dataobject;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class FileDO extends BaseDO {
    /**
     * 文件名
     */
    private String fileName;
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
