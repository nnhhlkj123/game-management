package com.example.game_management.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.game_management.dto.GameDto;
import com.example.game_management.model.Game;

public interface GameService {
	Page<Game> getGames(String category, String keyword, Pageable pageable);
    Game getById(String id);
    void save(GameDto dto);
    void delete(List<String> ids);
}

