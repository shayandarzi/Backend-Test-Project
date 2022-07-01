package io.egroup.backendtestproject.repository;

import io.egroup.backendtestproject.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
