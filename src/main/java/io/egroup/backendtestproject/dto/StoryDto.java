package io.egroup.backendtestproject.dto;

import io.egroup.backendtestproject.model.enums.StoryStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StoryDto extends IssueDto {

    @NotNull(message = "io.egroup.backend.error.invalid-data")
    private StoryStatus status;

    @NotNull(message = "io.egroup.backend.error.invalid-data")
    private Long point;
}
