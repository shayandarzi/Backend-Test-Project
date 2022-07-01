package io.egroup.backendtestproject.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "developer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String name;

    @OneToMany(mappedBy = "developer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Issue> issues;
}
