package io.egroup.backendtestproject.dto.response;

import io.egroup.backendtestproject.dto.BugDto;
import lombok.Data;

import java.util.List;

@Data
public class AllBugResponseDto extends BaseResponse {
    List<BugDto> bugDtos;

}
