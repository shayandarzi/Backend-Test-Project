package io.egroup.backendtestproject.dto.response;

import io.egroup.backendtestproject.dto.DeveloperDto;
import lombok.Data;

@Data
public class DeveloperResponseDto extends BaseResponse {
    private DeveloperDto developerDto;

}
