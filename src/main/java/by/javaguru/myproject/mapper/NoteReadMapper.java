package by.javaguru.myproject.mapper;

import by.javaguru.myproject.dto.NoteReadeDto;
import by.javaguru.myproject.dto.UserReadDto;
import by.javaguru.myproject.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NoteReadMapper implements Mapper<Note, NoteReadeDto> {

    private final UserReadMapper userReadMapper;

   @Override
    public NoteReadeDto map(Note object) {
        UserReadDto user = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElse(null);
        return new NoteReadeDto(
                object.getId(),
                object.getCreatedDate(),
                object.getFeeling(),
                object.getLifeSituation(),
                object.getTelexSensation(),
                object.getYourActions(),
                object.getMyThoughtsAboutOthers(),
                object.getMyThoughts(),
                user.getId()
        );
    }

}
