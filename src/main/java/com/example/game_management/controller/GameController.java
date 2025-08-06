package com.example.game_management.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.game_management.dto.GameDto;
import com.example.game_management.dto.GameNameDto;
import com.example.game_management.model.Game;
import com.example.game_management.service.GameService;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public String listGames(@RequestParam(required = false) String category,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(value = "page", defaultValue = "0") int page,
                            @RequestParam(value = "size", defaultValue = "5") int size,
                            Model model) {
        if (category != null && category.trim().isEmpty()) {
            category = null;
        }
        if (keyword != null && keyword.trim().isEmpty()) {
            keyword = null;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Game> gamePage = gameService.getGames(category, keyword, pageable);

        int startEntry = (int) (gamePage.getNumber() * gamePage.getSize()) + 1;
        int endEntry = Math.min(startEntry + gamePage.getNumberOfElements() - 1, (int) gamePage.getTotalElements());
        int totalEntries = (int) gamePage.getTotalElements();

        model.addAttribute("gamePage", gamePage);
        model.addAttribute("currentCategory", category);
        model.addAttribute("currentKeyword", keyword);
        model.addAttribute("startEntry", totalEntries == 0 ? 0 : startEntry);
        model.addAttribute("endEntry", totalEntries == 0 ? 0 : endEntry);
        model.addAttribute("totalEntries", totalEntries);

        return "game-list";
    }

    @GetMapping("/register")
    public String registerForm(@RequestParam(required = false) String id, Model model) {
        GameDto game = (id != null) ? convert(gameService.getById(id)) : new GameDto();
        model.addAttribute("game", game);
        return "game-form";
    }

    @PostMapping("/save")
    public String saveGame(@ModelAttribute GameDto game) {
        gameService.save(game);
        return "redirect:/games";
    }

    @PostMapping("/delete")
    public String deleteGames(@RequestParam List<String> ids) {
        gameService.delete(ids);
        return "redirect:/games";
    }
    
    private GameDto convert(Game game) {
        GameDto dto = new GameDto();
        dto.setId(game.getId());
        dto.setCategory(game.getCategory());

        List<GameNameDto> names = game.getNameList().stream().map(name -> {
            GameNameDto nameDto = new GameNameDto();
            nameDto.setLanguage(name.getLanguage());
            nameDto.setValue(name.getValue());
            return nameDto;
        }).collect(Collectors.toList());

        dto.setNames(names);
        return dto;
    }

}

