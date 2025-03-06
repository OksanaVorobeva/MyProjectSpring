package by.javaguru.myproject.service;

import by.javaguru.myproject.dto.NoteCreateEditDto;
import by.javaguru.myproject.dto.NoteReadeDto;
import by.javaguru.myproject.mapper.NoteCreateEditMapper;
import by.javaguru.myproject.mapper.NoteReadMapper;
import by.javaguru.myproject.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteReadMapper noteReadMapper;
    private final NoteCreateEditMapper noteCreateEditMapper;

    public List<NoteReadeDto> findAll() {
        return noteRepository.findAll().stream()
                .map(noteReadMapper::map)
                .toList();
    }

    public List<NoteReadeDto> findAllNoteByIdUser(Long id) {
        return noteRepository.findNote(id).stream()
                .map(noteReadMapper::map)
                .toList();

    }

    @Transactional
    public NoteReadeDto create(NoteCreateEditDto noteCreateEditDto) {
        return Optional.of(noteCreateEditDto)
                .map(dto -> {
                    return noteCreateEditMapper.map(dto);
                })
                .map(noteRepository::save)
                .map(noteReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<NoteReadeDto> update(Long id, NoteCreateEditDto noteCreateEditDto) {
        return noteRepository.findById(id)
                .map(entity -> {
                    return noteCreateEditMapper.map(noteCreateEditDto, entity);
                })
                .map(noteRepository::saveAndFlush)
                .map(noteReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return noteRepository.findById(id)
                .map(entity -> {
                    noteRepository.delete(entity);
                    noteRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
