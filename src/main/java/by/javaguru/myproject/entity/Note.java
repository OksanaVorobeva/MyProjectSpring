package by.javaguru.myproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "notes")
public class Note implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "feeling")
    private String feeling;

    @Column(name = "life_situation")
    private String lifeSituation;

    @Column(name = "telex_sensation")
    private String telexSensation;

    @Column(name = "your_actions")
    private String yourActions;

    @Column(name = "my_thoughts_about_others")
    private String myThoughtsAboutOthers;

    @Column(name = "my_thoughts")
    private String myThoughts;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}
