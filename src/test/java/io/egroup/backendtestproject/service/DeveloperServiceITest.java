package io.egroup.backendtestproject.service;

import io.egroup.backendtestproject.AbstractBaseIT;
import io.egroup.backendtestproject.dto.DeveloperDto;
import io.egroup.backendtestproject.dto.response.AllDevelopersResponseDto;
import io.egroup.backendtestproject.dto.response.DeveloperResponseDto;
import io.egroup.backendtestproject.model.Developer;
import io.egroup.backendtestproject.repository.DeveloperRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class DeveloperServiceITest extends AbstractBaseIT {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DeveloperRepository developerRepository;

    @AfterEach
    public void afterEach() {
        developerRepository.deleteAll();
    }


    @Test
    void givenCreateDeveloper_whenDataIsValid_thenReturnSuccessful() {
        DeveloperDto request = new DeveloperDto();
        request.setName("shayan");

        String uri = baseUrl(port) + "/developers";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<DeveloperResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, entity, DeveloperResponseDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertEquals("shayan", response.getBody().getDeveloperDto().getName());
    }

    @Test
    void givenCreateDeveloper_whenNameIsNull_thenExceptionShouldThrown() {
        DeveloperDto request = new DeveloperDto();
        request.setName(null);

        String uri = baseUrl(port) + "/developers";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<DeveloperResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, entity, DeveloperResponseDto.class);


        assertEquals(400, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccessful());
        assertNotNull(response.getBody().getErrorData());
        assertEquals("data is invalid", response.getBody().getErrorData().getMessage());
    }

    @Test
    void givenUpdateDeveloper_whenDataIsValid_thenReturnSuccessful() {
        Developer developer = saveDeveloper();
        DeveloperDto request = new DeveloperDto();
        request.setName("shayan darzi");

        String uri = baseUrl(port) + "/developers/" + developer.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<DeveloperResponseDto> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, DeveloperResponseDto.class);


        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertEquals("shayan darzi", response.getBody().getDeveloperDto().getName());
        assertEquals(developer.getId(), response.getBody().getDeveloperDto().getId());
    }

    @Test
    void givenUpdateDeveloper_whenEntityNotFound_thenReturnException() {
        Developer developer = saveDeveloper();
        DeveloperDto request = new DeveloperDto();
        request.setName("shayan darzi");

        String uri = baseUrl(port) + "/developers/" + 1100;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<DeveloperResponseDto> response = restTemplate.exchange(uri, HttpMethod.PUT, entity, DeveloperResponseDto.class);

        assertEquals(422, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccessful());
        assertNotNull(response.getBody().getErrorData());
        assertEquals("Developer entity by id: 1100 not found", response.getBody().getErrorData().getMessage());
    }

    @Test
    void givenGetDeveloper_whenDataIsValid_thenReturnSuccessful() {
        Developer developer = saveDeveloper();

        String uri = baseUrl(port) + "/developers";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<AllDevelopersResponseDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, AllDevelopersResponseDto.class);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccessful());
        assertNull(response.getBody().getErrorData());
        assertEquals("shayan", response.getBody().getDeveloperDtos().get(0).getName());
    }


    private Developer saveDeveloper() {
        Developer developer = new Developer();
        developer.setName("shayan");
        return developerRepository.save(developer);
    }
}
