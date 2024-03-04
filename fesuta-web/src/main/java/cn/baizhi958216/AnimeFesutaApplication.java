package cn.baizhi958216;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import cn.baizhi958216.core.StorageProperties;
import cn.baizhi958216.service.FileService;
import cn.baizhi958216.service.ImageService;

@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class AnimeFesutaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimeFesutaApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FileService fileService, ImageService imageService) {
        return args -> {
            fileService.init();
            imageService.init();
        };
    }
}