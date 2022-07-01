package io.egroup.backendtestproject.repository;

import io.egroup.backendtestproject.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {
}
