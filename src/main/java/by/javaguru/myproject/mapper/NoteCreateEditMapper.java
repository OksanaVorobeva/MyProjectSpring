package by.javaguru.myproject.mapper;

import by.javaguru.myproject.dto.NoteCreateEditDto;
import by.javaguru.myproject.entity.Note;
import by.javaguru.myproject.entity.User;
import by.javaguru.myproject.repository.UserRepository;
import by.javaguru.myproject.service.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;




@Component
@RequiredArgsConstructor
public class NoteCreateEditMapper implements Mapper<NoteCreateEditDto, Note> {

    private final UserRepository userRepository;

    @Override
    public Note map(NoteCreateEditDto fromObject, Note toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Note map(NoteCreateEditDto object) {
        Note note = new Note();
        copy(object, note);
        return note;
    }

    private void copy(NoteCreateEditDto object, Note note) {
        note.setFeeling(object.getFeeling());
        note.setLifeSituation(object.getLifeSituation());
        note.setTelexSensation(object.getTelexSensation());
        note.setYourActions(object.getYourActions());
        note.setMyThoughtsAboutOthers(object.getMyThoughtsAboutOthers());
        note.setMyThoughts(object.getMyThoughts());

        note.setUser(getUser(object));
    }

    private User getUser(NoteCreateEditDto object) {
        return userRepository.findById(getUserId())
                .orElse(null);
    }

    private Long getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getId();
        }
        return null;
    }
}

