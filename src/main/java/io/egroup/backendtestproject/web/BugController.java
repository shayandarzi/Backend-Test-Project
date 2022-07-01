package io.egroup.backendtestproject.web;

import io.egroup.backendtestproject.dto.BugDto;
import io.egroup.backendtestproject.dto.response.AllBugResponseDto;
import io.egroup.backendtestproject.dto.response.BugResponseDto;
import io.egroup.backendtestproject.service.BugService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/bugs")
public class BugController {

    private final BugService bugService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BugResponseDto> addBug(@RequestBody @Valid BugDto bugDto) {
        return ResponseEntity.ok(bugService.createBug(bugDto));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBug(@PathVariable("id") Long id) {
        bugService.deleteBug(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BugResponseDto> updateBug(@PathVariable("id") Long id, @RequestBody @Valid BugDto bugDto) {
        return ResponseEntity.ok(bugService.updateBug(id, bugDto));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllBugResponseDto> getBugs() {
        return ResponseEntity.ok(bugService.getBugs());
    }

    @GetMapping(path = "/{bugId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BugResponseDto> getBugDetail(@PathVariable("bugId") Long bugId) {
        return ResponseEntity.ok(bugService.getBug(bugId));
    }
}
