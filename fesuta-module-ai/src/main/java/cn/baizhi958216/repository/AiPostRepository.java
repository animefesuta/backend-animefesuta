package cn.baizhi958216.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.baizhi958216.dataobjects.AiPostDO;

@Repository
public interface AiPostRepository extends JpaRepository<AiPostDO, Object> {
}
