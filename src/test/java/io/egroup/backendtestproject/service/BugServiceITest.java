package io.egroup.backendtestproject.service;

import io.egroup.backendtestproject.AbstractBaseIT;
import io.egroup.backendtestproject.dto.BugDto;
import io.egroup.backendtestproject.dto.response.AllBugResponseDto;
import io.egroup.backendtestproject.dto.response.BugResponseDto;
import io.egroup.backendtestproject.model.Bug;
import io.egroup.backendtestproject.model.enums.BugStatus;
import io.egroup.backendtestproject.model.enums.Priority;
import io.egroup.backendtestproject.repository.BugRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BugServiceITest extends AbstractBaseIT {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BugRepository bugRepository;

    @BeforeEach
    public void beforeEach() {
        bugRepository.deleteAll();
    }


    @Test
    void givenCreateBug_whenDataIsValid_thenReturnSuccessful() {
        BugDto request = new BugDto();
        request.setDescription("Test");
        request.setTitle("First");
        request.setStatus(BugStatus.NEW);
        request.setPriority(Priority.CRITICAL);

        String uri = baseUrl(port) + "/bugs";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<BugResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, entity, BugResponseDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertNotNull(response.getBody().getBugDto());
        BugDto bugDto = response.getBody().getBugDto();
        assertEquals("Test", bugDto.getDescription());
        assertEquals("First", bugDto.getTitle());
        assertEquals(BugStatus.NEW, bugDto.getStatus());
        assertEquals(Priority.CRITICAL, bugDto.getPriority());
    }

    @Test
    void givenCreateBug_whenTitleIsNull_thenExceptionShouldThrown() {
        BugDto request = new BugDto();
        request.setDescription("Test");
        request.setTitle(null);
        request.setStatus(BugStatus.NEW);
        request.setPriority(Priority.CRITICAL);

        String uri = baseUrl(port) + "/bugs";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<BugResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, entity, BugResponseDto.class);


        assertEquals(400, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccessful());
        assertNotNull(response.getBody().getErrorData());
        assertEquals("data is invalid", response.getBody().getErrorData().getMessage());
    }

    @Test
    void givenUpdateBug_whenDataIsValid_thenReturnSuccessful() {
        Bug bug = saveBug();
        BugDto request = new BugDto();
        request.setDescription("Test");
        request.setTitle("First");
        request.setStatus(BugStatus.NEW);
        request.setPriority(Priority.CRITICAL);

        String uri = baseUrl(port) + "/bugs/" + bug.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<BugResponseDto> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, BugResponseDto.class);


        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertNotNull(response.getBody().getBugDto());
        BugDto bugDto = response.getBody().getBugDto();
        assertEquals("Test", bugDto.getDescription());
        assertEquals("First", bugDto.getTitle());
        assertEquals(BugStatus.NEW, bugDto.getStatus());
        assertEquals(Priority.CRITICAL, bugDto.getPriority());
    }

    @Test
    void givenUpdateBug_whenEntityNotFound_thenReturnException() {
        Bug bug = saveBug();
        BugDto request = new BugDto();
        request.setDescription("Test");
        request.setTitle("First");
        request.setStatus(BugStatus.NEW);
        request.setPriority(Priority.MAJOR);

        String uri = baseUrl(port) + "/bugs/" + 1100;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<BugResponseDto> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, BugResponseDto.class);

        assertEquals(422, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccessful());
        assertNotNull(response.getBody().getErrorData());
        assertEquals("Bug entity by id: 1100 not found.", response.getBody().getErrorData().getMessage());
    }

    @Test
    void givenGetBug_whenDataIsValid_thenReturnSuccessful() {
        Bug bug = saveBug();
        String uri = baseUrl(port) + "/bugs";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<AllBugResponseDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, AllBugResponseDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertNotNull(response.getBody().getBugDtos());
        BugDto bugDto = response.getBody().getBugDtos().get(0);
        assertEquals("Test", bugDto.getDescription());
        assertEquals("First", bugDto.getTitle());
        assertEquals(BugStatus.NEW, bugDto.getStatus());
        assertEquals(Priority.MAJOR, bugDto.getPriority());
    }


    private Bug saveBug() {
        Bug bug = new Bug();
        bug.setDescription("Test");
        bug.setTitle("First");
        bug.setStatus(BugStatus.NEW);
        bug.setPriority(Priority.MAJOR);
        bug.setIssueId(UUID.randomUUID().toString());
        return bugRepository.save(bug);
    }
}
