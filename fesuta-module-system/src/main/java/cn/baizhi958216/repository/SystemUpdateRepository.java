package cn.baizhi958216.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobject.SystemUpdateDO;

@Repository
public interface SystemUpdateRepository extends JpaRepository<SystemUpdateDO, Object> {
    @Query(value = "select * from fesuta_system_update WHERE deleted=FALSE ORDER BY create_time DESC LIMIT 1", nativeQuery = true)
    SystemUpdateDO findLatest();
}
