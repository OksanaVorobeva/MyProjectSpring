package by.javaguru.myproject.service;

import by.javaguru.myproject.dto.DirectoryCreateEditDto;
import by.javaguru.myproject.dto.DirectoryReadDto;

import java.util.List;
import java.util.Optional;

public interface DirectoryService {
    List<DirectoryReadDto> findAll();

    Optional<DirectoryReadDto> findById(Long id);

    DirectoryReadDto create(DirectoryCreateEditDto directoryCreateEditDto);

    Optional<DirectoryReadDto> update(Long id, DirectoryCreateEditDto updateDto);

    boolean delete(Long id);
}
