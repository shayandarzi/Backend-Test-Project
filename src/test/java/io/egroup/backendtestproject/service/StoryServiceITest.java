package io.egroup.backendtestproject.service;

import io.egroup.backendtestproject.AbstractBaseIT;
import io.egroup.backendtestproject.dto.StoryDto;
import io.egroup.backendtestproject.dto.response.AllStoriesResponseDto;
import io.egroup.backendtestproject.dto.response.StoryResponseDto;
import io.egroup.backendtestproject.model.Story;
import io.egroup.backendtestproject.model.enums.StoryStatus;
import io.egroup.backendtestproject.repository.StoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoryServiceITest extends AbstractBaseIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StoryRepository storyRepository;

    @BeforeEach
    public void beforeEach() {
        storyRepository.deleteAll();
    }


    @Test
    void givenCreateStory_whenDataIsValid_thenReturnSuccessful() {
        StoryDto request = new StoryDto();
        request.setDescription("Test");
        request.setTitle("First");
        request.setStatus(StoryStatus.NEW);
        request.setPoint(10L);

        String uri = baseUrl(port) + "/story";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<StoryResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, entity, StoryResponseDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertNotNull(response.getBody().getStoryDto());
        StoryDto storyDto = response.getBody().getStoryDto();
        assertEquals("Test", storyDto.getDescription());
        assertEquals("First", storyDto.getTitle());
        assertEquals(StoryStatus.NEW, storyDto.getStatus());
        assertEquals(10, storyDto.getPoint());
    }

    @Test
    void givenCreateStory_whenTitleIsNull_thenExceptionShouldThrown() {
        StoryDto request = new StoryDto();
        request.setDescription("Test");
        request.setTitle(null);
        request.setStatus(StoryStatus.NEW);
        request.setPoint(10L);

        String uri = baseUrl(port) + "/story";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<StoryResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, entity, StoryResponseDto.class);


        assertEquals(400, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccessful());
        assertNotNull(response.getBody().getErrorData());
        assertEquals("data is invalid", response.getBody().getErrorData().getMessage());
    }

    @Test
    void givenUpdateStory_whenDataIsValid_thenReturnSuccessful() {
        Story story = saveStory();
        StoryDto request = new StoryDto();
        request.setDescription("Test");
        request.setTitle("First");
        request.setStatus(StoryStatus.ESTIMATED);
        request.setPoint(10L);

        String uri = baseUrl(port) + "/story/" + story.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<StoryResponseDto> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, StoryResponseDto.class);


        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertNotNull(response.getBody().getStoryDto());
        StoryDto storyDto = response.getBody().getStoryDto();
        assertEquals("Test", storyDto.getDescription());
        assertEquals("First", storyDto.getTitle());
        assertEquals(StoryStatus.ESTIMATED, storyDto.getStatus());
        assertEquals(10, storyDto.getPoint());
    }

    @Test
    void givenUpdateStory_whenEntityNotFound_thenReturnException() {
        Story story = saveStory();
        StoryDto request = new StoryDto();
        request.setDescription("Test");
        request.setTitle("First");
        request.setStatus(StoryStatus.ESTIMATED);
        request.setPoint(10L);

        String uri = baseUrl(port) + "/story/" + 1100;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<StoryResponseDto> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, StoryResponseDto.class);

        assertEquals(422, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccessful());
        assertNotNull(response.getBody().getErrorData());
        assertEquals("Story entity by id: 1100 not found", response.getBody().getErrorData().getMessage());
    }

    @Test
    void givenGetStory_whenDataIsValid_thenReturnSuccessful() {
        Story story = saveStory();
        String uri = baseUrl(port) + "/story";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<AllStoriesResponseDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, AllStoriesResponseDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertNotNull(response.getBody().getStories());
        StoryDto storyDto = response.getBody().getStories().get(0);
        assertEquals("Test", storyDto.getDescription());
        assertEquals("First", storyDto.getTitle());
        assertEquals(StoryStatus.NEW, storyDto.getStatus());
        assertEquals(10, storyDto.getPoint());
    }


    private Story saveStory() {
        Story story = new Story();
        story.setDescription("Test");
        story.setTitle("First");
        story.setStatus(StoryStatus.NEW);
        story.setPoint(10L);
        story.setIssueId(UUID.randomUUID().toString());
        return storyRepository.save(story);
    }
}
