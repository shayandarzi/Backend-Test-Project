package io.egroup.backendtestproject.service.impl;

import io.egroup.backendtestproject.dto.DeveloperDto;
import io.egroup.backendtestproject.dto.StoryDto;
import io.egroup.backendtestproject.dto.response.PlanResponseDto;
import io.egroup.backendtestproject.exception.UnProcessableRequestException;
import io.egroup.backendtestproject.mapper.PlanMapper;
import io.egroup.backendtestproject.service.DeveloperService;
import io.egroup.backendtestproject.service.PlanService;
import io.egroup.backendtestproject.service.StoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
@Data
public class PlanServiceImpl implements PlanService {

    private final StoryService storyService;
    private final DeveloperService developerService;
    private final PlanMapper planMapper;

    @Override
    public PlanResponseDto plan() {
        List<StoryDto> estimatedStories = storyService.getEstimatedStories();
        List<DeveloperDto> developerDtos = developerService.getDevelopers().getDeveloperDtos();
        int developersSize = developerDtos.size();
        if (developersSize < 2) {
            throw new UnProcessableRequestException("Insufficient developers");
        }
        estimatedStories.sort(Comparator.comparingLong(StoryDto::getPoint));

        int capacityPerWeek = developersSize * 10;
        List<List<StoryDto>> plan = new ArrayList<>();
        int sum = 0;
        List<StoryDto> storyDtos = new ArrayList<>();
        for (int i = 0; i < estimatedStories.size(); i++) {
            long point = estimatedStories.get(i).getPoint();
            if (sum + point > capacityPerWeek) {
                sum = 0;
                plan.add(new ArrayList<>(storyDtos));
                storyDtos.clear();
                if (i != estimatedStories.size() - 1) {
                    storyDtos.add(estimatedStories.get(i));
                    sum += point;
                }

            } else if ((i != estimatedStories.size() - 1)) {
                sum += point;
                storyDtos.add(estimatedStories.get(i));
            }
            if (i == estimatedStories.size() - 1) {
                sum += point;
                storyDtos.add(estimatedStories.get(i));
                plan.add(new ArrayList<>(storyDtos));
            }
        }
        return planMapper.toPlanResponseDto(plan);
    }
}
