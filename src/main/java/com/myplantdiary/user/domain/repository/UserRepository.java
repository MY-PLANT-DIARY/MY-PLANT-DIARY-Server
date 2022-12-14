package com.myplantdiary.user.domain.repository;

import com.myplantdiary.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUid(String uid);

    User findByPw(String pw);
}
