package io.egroup.backendtestproject.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class DeveloperDto implements Serializable {

    private Long id;

    @NotNull(message = "io.egroup.backend.error.invalid-data")
    private String name;
}
