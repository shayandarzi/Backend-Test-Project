package io.egroup.backendtestproject.model;

import io.egroup.backendtestproject.model.enums.BugStatus;
import io.egroup.backendtestproject.model.enums.Priority;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bug")
@ToString(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Bug extends Issue {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BugStatus status;

}
