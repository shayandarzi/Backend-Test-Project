package io.egroup.backendtestproject.service;

import io.egroup.backendtestproject.dto.BugDto;
import io.egroup.backendtestproject.dto.response.AllBugResponseDto;
import io.egroup.backendtestproject.dto.response.BugResponseDto;

public interface BugService {
    BugResponseDto createBug(BugDto bugDto);

    void deleteBug(Long id);

    BugResponseDto updateBug(Long id, BugDto bugDto);

    BugResponseDto getBug(Long bugId);

    AllBugResponseDto getBugs();
}
