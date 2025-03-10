package by.javaguru.myproject.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class NoteReadeDto {

    Long id;
    LocalDate createdDate;
    String feeling;
    String lifeSituation;
    String telexSensation;
    String yourActions;
    String myThoughtsAboutOthers;
    String myThoughts;
    //  UserReadDto userId;
    Long userId;

}
