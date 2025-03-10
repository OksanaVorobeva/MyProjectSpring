package by.javaguru.myproject.repository;



import by.javaguru.myproject.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "SELECT u.* FROM notes u WHERE u.user_id = :id",
            nativeQuery = true)
   List<Note> findNote(Long id);


}
