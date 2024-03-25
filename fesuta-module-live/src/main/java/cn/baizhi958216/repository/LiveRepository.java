package cn.baizhi958216.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobject.LiveDO;

@Repository
public interface LiveRepository extends JpaRepository<LiveDO, Object> {
    LiveDO[] findByCreator(String uid);

    @Query(value = "SELECT * FROM fesuta_live WHERE creator=?1 AND deleted=FALSE", nativeQuery = true)
    LiveDO findByCreatorAndDeleteIsFalse(String uid);

    @Query(value = "SELECT * FROM fesuta_live WHERE deleted=FALSE ORDER BY create_time DESC LIMIT 1", nativeQuery = true)
    LiveDO findLatestRoom();
}
