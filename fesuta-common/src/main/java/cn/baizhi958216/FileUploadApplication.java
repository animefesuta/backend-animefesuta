package cn.baizhi958216;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import cn.baizhi958216.core.StorageProperties;
import cn.baizhi958216.service.FileService;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class FileUploadApplication {
    @Bean
    CommandLineRunner init(FileService storageService) {
        return args -> storageService.init();
    }
}
