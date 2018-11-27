package com.akvasoft.scrape_agents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepo extends JpaRepository<Code, Integer> {
    List<Code> findAllByCodeBetween(String start, String end);
}
