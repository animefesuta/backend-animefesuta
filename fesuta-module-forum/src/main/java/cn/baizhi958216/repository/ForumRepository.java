package cn.baizhi958216.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobject.ForumPostDO;

@Repository
public interface ForumRepository extends JpaRepository<ForumPostDO, Object> {
    ForumPostDO[] findAllByTheme(String theme);

    ForumPostDO[] findAllByRecommend(Boolean recommend);

    @Query(value = "SELECT * FROM fesuta_forum_post WHERE deleted=false ORDER BY post_forum_clickcount DESC LIMIT ?1", nativeQuery = true)
    ForumPostDO[] findAllByClickCount(int count);
}
