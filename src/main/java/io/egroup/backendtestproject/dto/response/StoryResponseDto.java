package io.egroup.backendtestproject.dto.response;

import io.egroup.backendtestproject.dto.StoryDto;
import lombok.Data;

@Data
public class StoryResponseDto extends BaseResponse {
    private StoryDto storyDto;
}
