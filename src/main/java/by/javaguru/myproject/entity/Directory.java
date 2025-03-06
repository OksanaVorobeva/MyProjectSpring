package by.javaguru.myproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "directory")
public class Directory implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feeling")
    private String feeling;

    @Column(name = "example_situation")
    private String exampleSituation;

    @Column(name = "bodily_sensations")
    private String bodilySensations;

    @Column(name = "thoughts")
    private String thoughts;

    @Column(name = "behavior")
    private String behavior;
}
