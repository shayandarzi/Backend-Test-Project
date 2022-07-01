package io.egroup.backendtestproject.mapper;

import io.egroup.backendtestproject.dto.PlanDto;
import io.egroup.backendtestproject.dto.StoryDto;
import io.egroup.backendtestproject.dto.response.PlanResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PlanMapper {

    default PlanResponseDto toPlanResponseDto(List<List<StoryDto>> lists) {
        List<PlanDto> planDtos = new ArrayList<>();
        PlanResponseDto planResponseDto = new PlanResponseDto();
        int weekCounter = 1;
        for (List<StoryDto> planList : lists) {
            PlanDto planDto = new PlanDto();
            planDto.setWeekNumber("week " + weekCounter);
            planDto.setStoryDtos(planList);
            planDtos.add(planDto);
            weekCounter++;
        }

        planResponseDto.setSuccessful(true);
        planResponseDto.setErrorData(null);
        planResponseDto.setPlanDtos(planDtos);
        planResponseDto.setNumberOfWeeks(planDtos.size());
        return planResponseDto;
    }
}
