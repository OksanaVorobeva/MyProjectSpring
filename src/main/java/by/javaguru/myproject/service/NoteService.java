package by.javaguru.myproject.service;

import by.javaguru.myproject.dto.NoteCreateEditDto;
import by.javaguru.myproject.dto.NoteReadeDto;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    Optional<NoteReadeDto> findById(Long id);

    List<NoteReadeDto> findAllNoteByIdUser(Long id);

    NoteReadeDto create(NoteCreateEditDto noteCreateEditDto,
                        Long userId);

    Optional<NoteReadeDto> update(Long id, NoteCreateEditDto noteCreateEditDto, Long userId);

    boolean delete(Long id, Long userId);
}
