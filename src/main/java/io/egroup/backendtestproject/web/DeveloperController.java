package io.egroup.backendtestproject.web;

import io.egroup.backendtestproject.dto.DeveloperDto;
import io.egroup.backendtestproject.dto.response.AllDevelopersResponseDto;
import io.egroup.backendtestproject.dto.response.DeveloperResponseDto;
import io.egroup.backendtestproject.service.impl.DeveloperServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperServiceImpl developerServiceImpl;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperResponseDto> addDeveloper(@RequestBody @Valid DeveloperDto developerDto) {
        return ResponseEntity.ok(developerServiceImpl.createDeveloper(developerDto));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteDeveloper(@PathVariable("id") Long id) {
        developerServiceImpl.deleteDeveloper(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperResponseDto> updateDeveloper(@PathVariable("id") Long id, @RequestBody @Valid DeveloperDto developerDto) {
        return ResponseEntity.ok(developerServiceImpl.updateDeveloper(id, developerDto));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeveloperResponseDto> getDevelopers(@PathVariable("id") Long id) {
        return ResponseEntity.ok(developerServiceImpl.getDeveloper(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AllDevelopersResponseDto> getDevelopers() {
        return ResponseEntity.ok(developerServiceImpl.getDevelopers());
    }
}
