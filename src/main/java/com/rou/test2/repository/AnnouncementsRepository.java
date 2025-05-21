package com.rou.test2.repository;

import com.rou.test2.model.Announcement;
import com.rou.test2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementsRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByUser(User user);
}
