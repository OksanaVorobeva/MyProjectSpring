package by.javaguru.myproject.service.impl;

import by.javaguru.myproject.dto.NoteCreateEditDto;
import by.javaguru.myproject.dto.NoteReadeDto;
import by.javaguru.myproject.dto.UserReadDto;
import by.javaguru.myproject.entity.Note;
import by.javaguru.myproject.mapper.NoteCreateEditMapper;
import by.javaguru.myproject.mapper.NoteReadMapper;
import by.javaguru.myproject.repository.NoteRepository;
import by.javaguru.myproject.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final NoteReadMapper noteReadMapper;
    private final NoteCreateEditMapper noteCreateEditMapper;

    public Optional<NoteReadeDto> findById(Long id) {
        return noteRepository.findById(id).map(noteReadMapper::map);
    }

    public List<NoteReadeDto> findAllNoteByIdUser(Long userId) {
        return noteRepository.findNote(userId).stream()
                .map(noteReadMapper::map)
                .toList();

    }
    @Transactional
    public NoteReadeDto create(NoteCreateEditDto noteCreateEditDto,
                               Long userId) {
        return Optional.of(noteCreateEditDto)
                .map(dto -> {
                    Note note = noteCreateEditMapper.map(dto);
                    return note;
                })
                .map(noteRepository::save)
                .map(noteReadMapper::map)
                .orElseThrow();
    }


    @Transactional
    public Optional<NoteReadeDto> update(Long id, NoteCreateEditDto noteCreateEditDto,Long userId) {
        return noteRepository.findById(id)
                .map(entity -> {
                    noteCreateEditMapper.map(noteCreateEditDto, entity);
                    noteRepository.saveAndFlush(entity);
                    return entity;
                })

                .map(noteReadMapper::map);
    }


    @Transactional
    public boolean delete(Long id,Long userId) {
        return noteRepository.findById(id)
                .map(entity -> {
                    noteRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

}
