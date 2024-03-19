package cn.baizhi958216.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import cn.baizhi958216.dataobject.FileDO;
import cn.baizhi958216.viewobject.FileVO;

public interface FileService {

    <S extends FileDO, T extends FileVO> T store(S s, T t, MultipartFile file, JpaRepository<S, Object> jpaRepository);
}
