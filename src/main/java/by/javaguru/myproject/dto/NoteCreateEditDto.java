package by.javaguru.myproject.dto;

import by.javaguru.myproject.entity.User;
import jakarta.persistence.*;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class NoteCreateEditDto {

    LocalDate createdDate;
    String feeling;
    String lifeSituation;
    String telexSensation;
    String yourActions;
    String myThoughtsAboutOthers;
    String myThoughts;
    Long userId;

}
