package com.akvasoft.scrape_agents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempRepo extends JpaRepository<Temp, Integer> {
}
