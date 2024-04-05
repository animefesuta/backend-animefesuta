package cn.baizhi958216.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobject.CommentDO;

@Repository
public interface CommentRepository extends JpaRepository<CommentDO, Object> {
    @Query(value = "SELECT * FROM fesuta_comments WHERE deleted=FALSE AND theme_id=?1 ORDER BY create_time DESC", nativeQuery = true)
    CommentDO[] findByThemeId(String id);
}
