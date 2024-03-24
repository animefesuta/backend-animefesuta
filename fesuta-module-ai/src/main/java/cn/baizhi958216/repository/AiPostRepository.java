package cn.baizhi958216.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobjects.AiPostDO;

@Repository
public interface AiPostRepository extends JpaRepository<AiPostDO, Object> {
    @Query(value = "select * from fesuta_ai_post_pic order by random() limit ?1", nativeQuery = true)
    List<AiPostDO> getAiPostsRandom(int limit);
}
