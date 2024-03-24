package cn.baizhi958216.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobject.ForumPostDO;

@Repository
public interface ForumRepository extends JpaRepository<ForumPostDO, Object> {
    ForumPostDO[] findAllByTheme(String theme);

    ForumPostDO[] findAllByRecommend(Boolean recommend);
}
