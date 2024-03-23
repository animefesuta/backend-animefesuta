package cn.baizhi958216.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobject.PicDO;

@Repository
public interface PostPicRepository extends JpaRepository<PicDO, Object> {
    List<PicDO> findAllByCreator(String UID);
}
