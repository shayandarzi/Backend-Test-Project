package io.egroup.backendtestproject.service.impl;

import io.egroup.backendtestproject.dto.BugDto;
import io.egroup.backendtestproject.dto.response.AllBugResponseDto;
import io.egroup.backendtestproject.dto.response.BugResponseDto;
import io.egroup.backendtestproject.exception.EntityNotFoundException;
import io.egroup.backendtestproject.mapper.BugMapper;
import io.egroup.backendtestproject.model.Bug;
import io.egroup.backendtestproject.repository.BugRepository;
import io.egroup.backendtestproject.service.BugService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class BugServiceImpl implements BugService {

    private BugRepository bugRepository;
    private BugMapper bugMapper;


    @Override
    public BugResponseDto createBug(BugDto bugDto) {
        Bug bug = bugMapper.toBug(bugDto);
        bug.setIssueId(UUID.randomUUID().toString());
        return bugMapper.toBugResponseDto(bugRepository.save(bug));
    }

    @Override
    public void deleteBug(Long id) {
        bugRepository.delete(findBugById(id));
    }

    @Override
    public BugResponseDto updateBug(Long id, BugDto bugDto) {
        Bug bug = findBugById(id);
        bug.setDescription(bugDto.getDescription());
        bug.setPriority(bugDto.getPriority());
        bug.setStatus(bugDto.getStatus());
        bug.setTitle(bugDto.getTitle());
        Bug updatedBug = bugRepository.save(bug);
        return bugMapper.toBugResponseDto(updatedBug);
    }

    @Override
    public BugResponseDto getBug(Long bugId) {
        Bug bugById = findBugById(bugId);
        return bugMapper.toBugResponseDto(bugById);
    }

    @Override
    public AllBugResponseDto getBugs() {
        List<Bug> all = bugRepository.findAll();
        return bugMapper.toAllBugResponseDto(all);
    }

    private Bug findBugById(Long id) {
        return bugRepository.findById(id).orElseThrow(() -> {
            log.error("Bug with id: {} not found.", id);
            throw new EntityNotFoundException("Bug entity by id: " + id + " not found.");
        });
    }
}
