package com.example.bai2.controller;

import com.example.bai2.model.Song;
import com.example.bai2.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private IService songService;

    @GetMapping("")
    public String showSongs(Model model) {
        List<Song> songs = songService.getAll();
        if (songs.isEmpty()) {
            model.addAttribute("message", "No songs found");
        }
        model.addAttribute("songs", songs);
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        return "create";
    }

    @PostMapping("/create")
    public String createSong(@Validated @ModelAttribute Song song, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "create";
        }
        songService.add(song);
        redirectAttributes.addFlashAttribute("message", "Create successfully!");
        return "redirect:/songs";
    }
    @GetMapping("/{id}/update")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Song song = songService.getById(id);
        model.addAttribute("song", song);
        return "update";
    }
    @PostMapping("/{id}/update")
    public String updateSong(@PathVariable int id ,@Validated @ModelAttribute Song song,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "update";
        }
        song.setId(id);
        songService.update(song);
        redirectAttributes.addFlashAttribute("message", "Update successfully!");
        return "redirect:/songs";
    }
}
