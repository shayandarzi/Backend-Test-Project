package io.egroup.backendtestproject.dto.response;

import io.egroup.backendtestproject.dto.StoryDto;
import lombok.Data;

import java.util.List;

@Data
public class AllStoriesResponseDto extends BaseResponse {
    private List<StoryDto> stories;
}
