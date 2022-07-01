package io.egroup.backendtestproject.mapper;

import io.egroup.backendtestproject.dto.DeveloperDto;
import io.egroup.backendtestproject.dto.response.AllDevelopersResponseDto;
import io.egroup.backendtestproject.dto.response.DeveloperResponseDto;
import io.egroup.backendtestproject.model.Developer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface DeveloperMapper {

    DeveloperDto toDeveloperDto(Developer developer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "issues", ignore = true)
    Developer toDeveloper(DeveloperDto developerDto);

    default DeveloperResponseDto toDeveloperResponseDto(Developer developer) {
        DeveloperResponseDto developerResponseDto = new DeveloperResponseDto();
        developerResponseDto.setSuccessful(true);
        developerResponseDto.setErrorData(null);
        developerResponseDto.setDeveloperDto(toDeveloperDto(developer));
        return developerResponseDto;
    }

    default AllDevelopersResponseDto toAllDevelopersResponseDto(List<Developer> developerList) {
        AllDevelopersResponseDto allDevelopersResponseDto = new AllDevelopersResponseDto();
        allDevelopersResponseDto.setSuccessful(true);
        allDevelopersResponseDto.setErrorData(null);
        allDevelopersResponseDto.setDeveloperDtos(developerList.stream().map(this::toDeveloperDto).collect(Collectors.toList()));
        return allDevelopersResponseDto;
    }
}
