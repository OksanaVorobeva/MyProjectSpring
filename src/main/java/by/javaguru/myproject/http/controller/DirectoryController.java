package by.javaguru.myproject.http.controller;

import by.javaguru.myproject.dto.DirectoryCreateEditDto;
import by.javaguru.myproject.dto.DirectoryReadDto;
import by.javaguru.myproject.service.DirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public String findAllDirectory(Model model) {
        List<DirectoryReadDto> directory = directoryService.findAll();
        model.addAttribute("directory", directory);
        return "directory/directory";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!directoryService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/directory";
    }

  @GetMapping("/{id}")
    public String updateDirectory(@PathVariable("id") Long id, Model model ) {
        return directoryService.findById(id)
                .map(directory -> {
                    model.addAttribute("directory", directory);
                    return "directory/directoryUpdate";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute DirectoryCreateEditDto directory) {
        return directoryService.update(id, directory)
                .map(it -> "redirect:/directory")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }



}

