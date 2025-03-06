package by.javaguru.myproject.mapper;

import by.javaguru.myproject.dto.DirectoryCreateEditDto;
import by.javaguru.myproject.entity.Directory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DirectoryCreateEditMapper implements Mapper<DirectoryCreateEditDto, Directory> {


    private void copy(DirectoryCreateEditDto from, Directory to) {
        to.setFeeling(from.getFeeling());
        to.setExampleSituation(from.getExampleSituation());
        to.setBodilySensations(from.getBodilySensations());
        to.setThoughts(from.getThoughts());
        to.setBehavior(from.getBehavior());
    }

    @Override
    public Directory map(DirectoryCreateEditDto object) {
        Directory directory = new Directory();
        copy(object, directory);
        return directory;
    }

    @Override
    public Directory map(DirectoryCreateEditDto fromObject, Directory toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
}
