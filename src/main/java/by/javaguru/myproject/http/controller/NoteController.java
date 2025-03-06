package by.javaguru.myproject.http.controller;

import by.javaguru.myproject.dto.*;
import by.javaguru.myproject.entity.Role;
import by.javaguru.myproject.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/{userId}")
    public String getAllNotes(@PathVariable("userId") Long userId, Model model) {
        List<NoteReadeDto> notes = noteService.findAllNoteByIdUser(userId);
        model.addAttribute("notes", notes);
        return "notes/notes";
    }
}
