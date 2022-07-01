package io.egroup.backendtestproject.web;

import io.egroup.backendtestproject.dto.StoryDto;
import io.egroup.backendtestproject.dto.response.AllStoriesResponseDto;
import io.egroup.backendtestproject.dto.response.StoryResponseDto;
import io.egroup.backendtestproject.service.impl.StoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/story")
public class StoryController {

    private final StoryServiceImpl storyService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoryResponseDto> addStory(@RequestBody @Valid StoryDto storyDto) {
        return ResponseEntity.ok(storyService.createStory(storyDto));
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoryResponseDto> updateStory(@PathVariable("id") Long id, @RequestBody StoryDto storyDto) {
        return ResponseEntity.ok(storyService.updateStory(id, storyDto));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteStory(@PathVariable("id") Long id) {
        storyService.deleteStory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoryResponseDto> getStory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(storyService.getStory(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllStoriesResponseDto> getStories() {
        return ResponseEntity.ok(storyService.getStories());
    }
}
