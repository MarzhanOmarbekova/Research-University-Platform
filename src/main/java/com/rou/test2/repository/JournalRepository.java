package com.rou.test2.repository;

import com.rou.test2.model.Journal;
import com.rou.test2.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
    Journal findBySchool(School school);
}

