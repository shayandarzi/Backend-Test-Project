package io.egroup.backendtestproject.service.impl;

import io.egroup.backendtestproject.dto.DeveloperDto;
import io.egroup.backendtestproject.dto.response.AllDevelopersResponseDto;
import io.egroup.backendtestproject.dto.response.DeveloperResponseDto;
import io.egroup.backendtestproject.exception.EntityNotFoundException;
import io.egroup.backendtestproject.mapper.DeveloperMapper;
import io.egroup.backendtestproject.model.Developer;
import io.egroup.backendtestproject.repository.DeveloperRepository;
import io.egroup.backendtestproject.service.DeveloperService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {

    private DeveloperMapper developerMapper;
    private DeveloperRepository developerRepository;

    @Override
    public DeveloperResponseDto createDeveloper(DeveloperDto developerDto) {
        return developerMapper.toDeveloperResponseDto(developerRepository.save(developerMapper.toDeveloper(developerDto)));
    }

    @Override
    public DeveloperResponseDto updateDeveloper(Long id, DeveloperDto developerDto) {
        Developer developer = findDeveloperById(id);
        developer.setName(developerDto.getName());
        return developerMapper.toDeveloperResponseDto(developerRepository.save(developer));
    }

    @Override
    public void deleteDeveloper(Long id) {
        developerRepository.delete(findDeveloperById(id));
    }

    @Override
    public DeveloperResponseDto getDeveloper(Long id) {
        return developerMapper.toDeveloperResponseDto(findDeveloperById(id));
    }

    @Override
    public AllDevelopersResponseDto getDevelopers() {
        List<Developer> all = developerRepository.findAll();
        return developerMapper.toAllDevelopersResponseDto(all);
    }

    private Developer findDeveloperById(Long id) {
        return developerRepository.findById(id).orElseThrow(() -> {
            log.error("Developer with id: {} not found.", id);
            throw new EntityNotFoundException("Developer entity by id: " + id + " not found");
        });
    }
}
