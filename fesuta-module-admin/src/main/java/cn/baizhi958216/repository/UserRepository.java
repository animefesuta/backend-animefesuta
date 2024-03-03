package cn.baizhi958216.repository;

import cn.baizhi958216.dataobject.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDO, String> {
    List<UserDO> findAllByNickname(String nickname);

    List<UserDO> findAllByNicknameLike(String string);

    Optional<UserDO> findByEmail(String email);
}
