package cn.baizhi958216.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobject.ImageDO;

@Repository
public interface ImageRepository extends JpaRepository<ImageDO, Object> {
}
