package by.javaguru.myproject.http.controller;

import by.javaguru.myproject.dto.*;
import by.javaguru.myproject.entity.Role;
import by.javaguru.myproject.entity.User;
import by.javaguru.myproject.service.CustomUserDetails;
import by.javaguru.myproject.service.NoteService;
import by.javaguru.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;


@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;


    @GetMapping
    public String getAllNotes(Model model) {
       Long userId = getUser().getId();
        List<NoteReadeDto> notes = noteService.findAllNoteByIdUser(userId);
        model.addAttribute("user", getUser());
        model.addAttribute("notes", notes);
        return "notes/notes";
    }



    @GetMapping("/{id}/delete")
    public String deleteNote(@PathVariable("id") Long noteId) {
        if (!noteService.delete(noteId, getUser().getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/notes";
    }

    @GetMapping("/{id}/update")
    public String updateDirectory(@PathVariable("id") Long id, Model model ) {
        return noteService.findById(id)
                .map(notes -> {
                    model.addAttribute("user", getUser());
                    model.addAttribute("notes", notes);
                    model.addAttribute("noteId", id);
                    model.addAttribute("isNew", false);
                    return "notes/noteForm";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute ("notes")
    NoteCreateEditDto notes) {
        return noteService.update(id, notes,getUser().getId())
                .map(it -> "redirect:/notes")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public String create(@ModelAttribute NoteCreateEditDto notes) {
        noteService.create(notes,getUser().getId());
        return "redirect:/notes";
    }

    @GetMapping("/create")
    public String createForm(Model model,@ModelAttribute ("notes") NoteCreateEditDto notes ) {
        model.addAttribute("user", getUser());
        model.addAttribute("notes", notes);
        model.addAttribute("isNew", true);
        return "notes/noteForm";
    }


    private User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            String username = ((CustomUserDetails) authentication.getPrincipal()).getUsername();
            return userService.findByUsername(username);
        }else {
            throw new IllegalStateException("User is not properly authenticated or MyUserDetails is not available.");
        }
    }
}