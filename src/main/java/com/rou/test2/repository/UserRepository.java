package com.rou.test2.repository;


import com.rou.test2.model.Journal;
import com.rou.test2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findBySubscriptions(Journal journal);
}
