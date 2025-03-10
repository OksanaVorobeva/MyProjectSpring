package by.javaguru.myproject.http.controller;

import by.javaguru.myproject.dto.DirectoryCreateEditDto;
import by.javaguru.myproject.dto.DirectoryReadDto;
import by.javaguru.myproject.entity.User;
import by.javaguru.myproject.service.CustomUserDetails;
import by.javaguru.myproject.service.DirectoryService;
import by.javaguru.myproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/directory")
public class DirectoryController {
    private final DirectoryService directoryService;
    private final UserService userService;

    @GetMapping
    public String findAllDirectory(Model model) {
        List<DirectoryReadDto> directory = directoryService.findAll();
        model.addAttribute("directory", directory);
        model.addAttribute("user", getUser());
        return "directory/directory";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!directoryService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/directory";
    }

    @GetMapping("/{id}/update")
    public String updateDirectory(@PathVariable("id") Long id, Model model) {
        return directoryService.findById(id)
                .map(directory -> {
                    model.addAttribute("user",getUser());
                    model.addAttribute("directory", directory);
                    model.addAttribute("directoryId", id);
                    model.addAttribute("isNew", false);
                    return "directory/directoryForm";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute("directory") DirectoryCreateEditDto directory) {
        return directoryService.update(id, directory)
                .map(it -> "redirect:/directory")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/create")
    public String create(@ModelAttribute DirectoryCreateEditDto createEditDto) {
        directoryService.create(createEditDto);
        return "redirect:/directory";
    }

    @GetMapping("/create")
    public String createForm(Model model, @ModelAttribute("directory") DirectoryCreateEditDto directory) {
        model.addAttribute("user", getUser());
        model.addAttribute("directory", directory);
        model.addAttribute("isNew", true);
        return "directory/directoryForm";
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

