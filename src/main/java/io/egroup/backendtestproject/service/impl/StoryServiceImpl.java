package io.egroup.backendtestproject.service.impl;

import io.egroup.backendtestproject.dto.StoryDto;
import io.egroup.backendtestproject.dto.response.AllStoriesResponseDto;
import io.egroup.backendtestproject.dto.response.StoryResponseDto;
import io.egroup.backendtestproject.exception.EntityNotFoundException;
import io.egroup.backendtestproject.mapper.StoryMapper;
import io.egroup.backendtestproject.model.Story;
import io.egroup.backendtestproject.model.enums.StoryStatus;
import io.egroup.backendtestproject.repository.StoryRepository;
import io.egroup.backendtestproject.service.StoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class StoryServiceImpl implements StoryService {

    private StoryRepository storyRepository;
    private StoryMapper storyMapper;


    @Override
    public StoryResponseDto createStory(StoryDto storyDto) {
        Story story = storyMapper.toStory(storyDto);
        story.setIssueId(UUID.randomUUID().toString());
        return storyMapper.toStoryResponseDto(storyRepository.save(story));
    }

    @Override
    public StoryResponseDto updateStory(Long id, StoryDto storyDto) {
        Story story = findStoryDtoById(id);
        story.setStatus(storyDto.getStatus());
        story.setPoint(storyDto.getPoint());
        story.setTitle(storyDto.getTitle());
        story.setDescription(storyDto.getDescription());
        return storyMapper.toStoryResponseDto((storyRepository.save(story)));
    }

    @Override
    public void deleteStory(Long id) {
        Story story = findStoryDtoById(id);
        storyRepository.delete(story);
    }

    @Override
    public StoryResponseDto getStory(Long id) {
        return storyMapper.toStoryResponseDto((findStoryDtoById(id)));
    }

    @Override
    public AllStoriesResponseDto getStories() {
        return storyMapper.toAllStoryResponseDto(storyRepository.findAll());
    }

    @Override
    public List<StoryDto> getEstimatedStories() {
        List<StoryDto> storyDtos = new ArrayList<>();
        List<Story> storiesByStatus = storyRepository.findStoriesByStatus(StoryStatus.ESTIMATED);
        storiesByStatus.forEach(story -> storyDtos.add(storyMapper.toStoryDto(story)));
        return storyDtos;
    }

    private Story findStoryDtoById(Long id) {
        return storyRepository.findById(id).orElseThrow(() -> {
            log.error("Story with id: {} not found.", id);
            throw new EntityNotFoundException("Story entity by id: " + id + " not found");
        });
    }
}
