package io.egroup.backendtestproject.dto.response;

import io.egroup.backendtestproject.dto.DeveloperDto;
import lombok.Data;

import java.util.List;

@Data
public class AllDevelopersResponseDto extends BaseResponse {
    private List<DeveloperDto> developerDtos;
}
