package cn.baizhi958216.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobject.FileDO;

@Repository
public interface FileRepository extends JpaRepository<FileDO, Object> {
}
