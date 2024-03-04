package cn.baizhi958216.viewobject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class FileVO extends BaseVO {
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
