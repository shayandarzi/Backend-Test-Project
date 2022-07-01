package io.egroup.backendtestproject.mapper;

import io.egroup.backendtestproject.dto.BugDto;
import io.egroup.backendtestproject.dto.response.AllBugResponseDto;
import io.egroup.backendtestproject.dto.response.BugResponseDto;
import io.egroup.backendtestproject.model.Bug;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BugMapper {


    BugDto toBugDto(Bug bug);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "status", source = "status")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "developer", ignore = true)
    Bug toBug(BugDto bugDto);

    default BugResponseDto toBugResponseDto(Bug bug) {
        BugResponseDto bugResponseDto = new BugResponseDto();
        bugResponseDto.setBugDto(toBugDto(bug));
        bugResponseDto.setSuccessful(true);
        return bugResponseDto;
    }

    default AllBugResponseDto toAllBugResponseDto(List<Bug> bugList) {
        AllBugResponseDto allBugResponseDto = new AllBugResponseDto();
        allBugResponseDto.setSuccessful(true);
        allBugResponseDto.setErrorData(null);
        allBugResponseDto.setBugDtos(bugList.stream().map(this::toBugDto).collect(Collectors.toList()));
        return allBugResponseDto;
    }
}
