package io.egroup.backendtestproject.model;

import io.egroup.backendtestproject.model.enums.StoryStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "story")
@ToString(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Story extends Issue {

    @Column(nullable = false)
    private long point;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoryStatus status;

}
