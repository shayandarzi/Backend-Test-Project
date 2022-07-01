package io.egroup.backendtestproject.dto.response;

import io.egroup.backendtestproject.dto.BugDto;
import lombok.Data;

@Data
public class BugResponseDto extends BaseResponse {
    private BugDto bugDto;
}
