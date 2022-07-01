package io.egroup.backendtestproject;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractBaseIT {

    public static String baseUrl(int port) {
        return "http://localhost:" + port + "/api/v1";
    }
}