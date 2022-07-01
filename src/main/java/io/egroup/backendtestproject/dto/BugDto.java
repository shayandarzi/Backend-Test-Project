package io.egroup.backendtestproject.dto;

import io.egroup.backendtestproject.model.enums.BugStatus;
import io.egroup.backendtestproject.model.enums.Priority;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BugDto extends IssueDto {

    @NotNull(message = "io.egroup.backend.error.invalid-data")
    private BugStatus status;

    @NotNull(message = "io.egroup.backend.error.invalid-data")
    private Priority priority;
}
