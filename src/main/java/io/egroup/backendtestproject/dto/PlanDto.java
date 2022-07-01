package io.egroup.backendtestproject.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PlanDto implements Serializable {
    private List<StoryDto> storyDtos;
    private String weekNumber;
}
