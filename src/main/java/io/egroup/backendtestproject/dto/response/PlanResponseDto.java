package io.egroup.backendtestproject.dto.response;

import io.egroup.backendtestproject.dto.PlanDto;
import lombok.Data;

import java.util.List;

@Data
public class PlanResponseDto extends BaseResponse {
    private List<PlanDto> planDtos;
    private long numberOfWeeks;
}
