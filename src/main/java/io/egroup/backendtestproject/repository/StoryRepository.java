package io.egroup.backendtestproject.repository;

import io.egroup.backendtestproject.model.Story;
import io.egroup.backendtestproject.model.enums.StoryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findStoriesByStatus(StoryStatus status);
}
