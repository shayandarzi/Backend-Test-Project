package io.egroup.backendtestproject.mapper;

import io.egroup.backendtestproject.dto.StoryDto;
import io.egroup.backendtestproject.dto.response.AllStoriesResponseDto;
import io.egroup.backendtestproject.dto.response.StoryResponseDto;
import io.egroup.backendtestproject.model.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StoryMapper {

    StoryDto toStoryDto(Story story);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "developer", ignore = true)
    Story toStory(StoryDto storyDto);

    default StoryResponseDto toStoryResponseDto(Story story) {
        StoryResponseDto storyResponseDto = new StoryResponseDto();
        storyResponseDto.setSuccessful(true);
        storyResponseDto.setErrorData(null);
        storyResponseDto.setStoryDto(toStoryDto(story));
        return storyResponseDto;
    }


    default AllStoriesResponseDto toAllStoryResponseDto(List<Story> stories) {
        AllStoriesResponseDto allDevelopersResponseDto = new AllStoriesResponseDto();
        allDevelopersResponseDto.setSuccessful(true);
        allDevelopersResponseDto.setErrorData(null);
        allDevelopersResponseDto.setStories(stories.stream().map(this::toStoryDto).collect(Collectors.toList()));
        return allDevelopersResponseDto;
    }
}
