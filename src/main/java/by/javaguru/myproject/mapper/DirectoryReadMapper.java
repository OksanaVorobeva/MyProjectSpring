package by.javaguru.myproject.mapper;

import by.javaguru.myproject.dto.DirectoryReadDto;
import by.javaguru.myproject.entity.Directory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DirectoryReadMapper implements Mapper<Directory, DirectoryReadDto> {

    @Override
    public DirectoryReadDto map(Directory object) {
        return new DirectoryReadDto(
                object.getId(),
                object.getFeeling(),
                object.getExampleSituation(),
                object.getBodilySensations(),
                object.getThoughts(),
                object.getBehavior()
        );
    }
}

