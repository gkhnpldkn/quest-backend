package com.gokhan.dkdemo.repos;

import com.gokhan.dkdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
