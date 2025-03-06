package by.javaguru.myproject.dto;


import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class DirectoryCreateEditDto {

    String feeling;
    String exampleSituation;
    String bodilySensations;
    String thoughts;
    String behavior;
}
