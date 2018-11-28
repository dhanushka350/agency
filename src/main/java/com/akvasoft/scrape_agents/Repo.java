package com.akvasoft.scrape_agents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Content, Integer> {
    Content findTopByOrderByIdDesc();
    boolean existsByCode(String code);
}
