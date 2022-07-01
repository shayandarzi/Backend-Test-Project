package io.egroup.backendtestproject.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public abstract class IssueDto implements Serializable {

    private long id;

    @NotNull(message = "io.egroup.backend.error.invalid-data")
    private String title;

    @NotNull(message = "io.egroup.backend.error.invalid-data")
    private String description;

    private String issueId;
}
