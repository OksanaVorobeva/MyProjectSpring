package by.javaguru.myproject.service;

import by.javaguru.myproject.dto.DirectoryCreateEditDto;
import by.javaguru.myproject.dto.DirectoryReadDto;

import by.javaguru.myproject.mapper.DirectoryCreateEditMapper;
import by.javaguru.myproject.mapper.DirectoryReadMapper;
import by.javaguru.myproject.repository.DirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DirectoryService {

    private final DirectoryRepository directoryRepository;
    private final DirectoryReadMapper directoryReadMapper;
    private final DirectoryCreateEditMapper directoryCreateEditMapper;

    public List<DirectoryReadDto> findAll() {
        return directoryRepository.findAll().stream()
                .map(directoryReadMapper::map)
                .toList();
    }

    public Optional<DirectoryReadDto> findById(Long id) {
        return directoryRepository.findById(id).map(directoryReadMapper::map);
    }

    @Transactional
    public DirectoryReadDto create(DirectoryCreateEditDto directoryCreateEditDto) {
        return Optional.of(directoryCreateEditDto)
                .map(dto -> {
                    return directoryCreateEditMapper.map(dto);
                })
                .map(directoryRepository::save)
                .map(directoryReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<DirectoryReadDto> update(Long id, DirectoryCreateEditDto updateDto) {
        return directoryRepository.findById(id)
                .map(entity -> {
                    return directoryCreateEditMapper.map(updateDto, entity);
                })
                .map(directoryRepository::saveAndFlush)
                .map(directoryReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return directoryRepository.findById(id)
                .map(entity -> {
                    directoryRepository.delete(entity);
                    directoryRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
