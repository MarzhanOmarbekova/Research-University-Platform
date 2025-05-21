package com.rou.test2.repository;

import com.rou.test2.model.ResearchPaper;
import com.rou.test2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResearchPaperRepository extends JpaRepository<ResearchPaper, Long> {
    List<ResearchPaper> findByUser(User user);
}
