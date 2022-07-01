package io.egroup.backendtestproject.service;

import io.egroup.backendtestproject.dto.DeveloperDto;
import io.egroup.backendtestproject.dto.response.AllDevelopersResponseDto;
import io.egroup.backendtestproject.dto.response.DeveloperResponseDto;

public interface DeveloperService {

    DeveloperResponseDto createDeveloper(DeveloperDto developerDto);

    DeveloperResponseDto updateDeveloper(Long id, DeveloperDto developerDto);

    void deleteDeveloper(Long id);

    DeveloperResponseDto getDeveloper(Long id);

    AllDevelopersResponseDto getDevelopers();
}
