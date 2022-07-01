package io.egroup.backendtestproject.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Table(name = "issue")
public class Issue {

    protected Issue() {
        this.creationDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "title", columnDefinition = "varchar(100)")
    private String title;

    @Column(name = "description", columnDefinition = "varchar(300)")
    private String description;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime creationDate;

    @Column(name = "issue_id")
    private String issueId;

    @ManyToOne
    private Developer developer;
}
