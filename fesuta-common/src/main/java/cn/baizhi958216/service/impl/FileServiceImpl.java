package cn.baizhi958216.service.impl;

import cn.baizhi958216.dataobject.FileDO;
import cn.baizhi958216.exception.StorageException;
import cn.baizhi958216.service.FileService;
import cn.baizhi958216.utils.BaseUserInfo;
import cn.baizhi958216.utils.FileMd5HashUtils;
import cn.baizhi958216.viewobject.FileVO;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    // 日期格式化
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yyy/MM/dd/");

    // 资源的 访问 URL
    @Value("${app.minio.base-url}")
    private String baseUrl;

    // API 端点
    @Value("${app.minio.endpoint}")
    private String endpoint;

    // Bucket 存储桶
    @Value("${app.minio.bucket}")
    private String bucket;

    // Acess Key
    @Value("${app.minio.access-key}")
    private String accessKey;

    // Secret Key
    @Value("${app.minio.secret-key}")
    private String secretKey;

    @Override
    public <S extends FileDO, T extends FileVO> T store(S s, T t, MultipartFile file,
            JpaRepository<S, Object> jpaRepository) {
        FileMd5HashUtils fileMd5HashUtils = new FileMd5HashUtils();
        try {
            if (file.isEmpty() || file.getOriginalFilename() == null) {
                throw new StorageException("上传文件内容为空！");
            }

            String contentType = file.getContentType();
            String fileOriginalName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String ext = "." + fileOriginalName.substring(fileOriginalName.lastIndexOf('.') + 1);

            // 根据日期打散目录，使用 UUID 重命名文件
            String filePath = formatter.format(LocalDate.now()) + uuid.toString().replace("-", "") + ext;

            // 实例化客户端
            MinioClient client = MinioClient.builder()
                    .endpoint(this.endpoint)
                    .credentials(this.accessKey, this.secretKey)
                    .build();
            // 上传文件到客户端
            try (InputStream inputStream = file.getInputStream()) {
                client.putObject(PutObjectArgs.builder()
                        .bucket(this.bucket) // 指定 Bucket
                        .contentType(contentType) // 指定 Content Type
                        .object(filePath) // 指定文件的路径
                        .stream(inputStream, file.getSize(), -1) // 文件的 Inputstream 流
                        .build());
            } catch (Exception e) {
                e.printStackTrace();
            }

            try (InputStream inputStream = file.getInputStream()) {
                String fileHash = fileMd5HashUtils.getFileHash256(inputStream);
                String userEmail = BaseUserInfo.get("username");
                s.setId(uuid);
                s.setCreator(userEmail);
                s.setOriginalFileName(file.getOriginalFilename());
                s.setFileName(uuid.toString().replace("-", "") + ext);
                s.setFileHash(fileHash);
                s.setCreateTime(LocalDateTime.now());
                s.setFilePath(filePath);
                s.setFileSize(file.getSize());
                s.setFileType(file.getContentType());

                jpaRepository.save(s);

                t.setId(s.getId());
                t.setOriginalFileName(file.getOriginalFilename());
                t.setFileName(uuid.toString().replace("-", "") + ext);
                t.setFileHash(fileHash);
                t.setCreateTime(s.getCreateTime());
                t.setFilePath(filePath);
                t.setFileSize(file.getSize());
                t.setFileType(file.getContentType());
            }
        } catch (IOException e) {
            throw new StorageException("文件保存失败！", e);
        }
        return t;
    }
}
