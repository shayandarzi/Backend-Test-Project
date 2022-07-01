package io.egroup.backendtestproject.service;

import io.egroup.backendtestproject.dto.StoryDto;
import io.egroup.backendtestproject.dto.response.AllStoriesResponseDto;
import io.egroup.backendtestproject.dto.response.StoryResponseDto;

import java.util.List;

public interface StoryService {

    StoryResponseDto createStory(StoryDto storyDto);

    StoryResponseDto updateStory(Long id, StoryDto storyDto);

    void deleteStory(Long id);

    StoryResponseDto getStory(Long id);

    AllStoriesResponseDto getStories();

    List<StoryDto> getEstimatedStories();
}
