package io.egroup.backendtestproject.service;

import io.egroup.backendtestproject.AbstractBaseIT;
import io.egroup.backendtestproject.dto.PlanDto;
import io.egroup.backendtestproject.dto.StoryDto;
import io.egroup.backendtestproject.dto.response.PlanResponseDto;
import io.egroup.backendtestproject.model.Developer;
import io.egroup.backendtestproject.model.Story;
import io.egroup.backendtestproject.model.enums.StoryStatus;
import io.egroup.backendtestproject.repository.DeveloperRepository;
import io.egroup.backendtestproject.repository.StoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PlanServiceITest extends AbstractBaseIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @BeforeEach
    public void beforeEach() {
        storyRepository.deleteAll();
        developerRepository.deleteAll();
    }


    @Test
    void givenCreateDeveloper_whenDataIsValid_thenReturnSuccessful() {
        saveDeveloper("Shayan");
        saveDeveloper("Behnam");
        saveDeveloper("Andre");

        saveStory("story1", "story1", 15L);
        saveStory("story2", "story2", 12L);
        saveStory("story3", "story3", 16L);
        saveStory("story4", "story4", 13L);
        saveStory("story5", "story5", 9L);


        String uri = baseUrl(port) + "/plan";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<PlanResponseDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, PlanResponseDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertNotNull(response.getBody().getPlanDtos());

        List<PlanDto> planDtos = response.getBody().getPlanDtos();
        assertEquals(3, planDtos.size());
        planDtos.forEach(planDto -> {
            int sumOfStoryPointsPerWeek = 0;
            for (StoryDto storyDto : planDto.getStoryDtos()) {
                sumOfStoryPointsPerWeek += storyDto.getPoint();
            }
            assertTrue(sumOfStoryPointsPerWeek <= 30);
        });

    }

    private Developer saveDeveloper(String name) {
        Developer developer = new Developer();
        developer.setName(name);
        return developerRepository.save(developer);
    }

    private Story saveStory(String description, String title, Long storyPoint) {
        Story story = new Story();
        story.setDescription(description);
        story.setTitle(title);
        story.setIssueId(UUID.randomUUID().toString());
        story.setStatus(StoryStatus.ESTIMATED);
        story.setPoint(storyPoint);
        return storyRepository.save(story);
    }

}
