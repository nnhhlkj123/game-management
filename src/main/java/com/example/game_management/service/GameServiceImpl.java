package com.example.game_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.game_management.dto.GameDto;
import com.example.game_management.model.Game;
import com.example.game_management.model.GameName;
import com.example.game_management.repository.GameRepository;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepo;

    @Override
    public Game getById(String id) {
        return gameRepo.findById(id).orElse(null);
    }

    @Override
    public void save(GameDto dto) {
        Game game = new Game();
        game.setId(dto.getId());
        game.setCategory(dto.getCategory());

        List<GameName> names = dto.getNames().stream().map(n -> {
            GameName name = new GameName();
            name.setLanguage(n.getLanguage());
            name.setValue(n.getValue());
            name.setGame(game);
            return name;
        }).collect(Collectors.toList());

        game.setNameList(names);
        gameRepo.save(game);
    }

    @Override
    public void delete(List<String> ids) {
        ids.forEach(gameRepo::deleteById);
    }

	@Override
	public Page<Game> getGames(String category, String keyword, Pageable pageable) {
		// Nếu cả hai filter đều null/empty, có thể gọi findAll
        if ((category == null || category.isBlank()) && (keyword == null || keyword.isBlank())) {
            return gameRepo.findAll(pageable);
        }
        String cat = (category == null || category.isBlank()) ? null : category;
        String kw = (keyword == null || keyword.isBlank()) ? null : keyword;
        return gameRepo.searchByCategoryAndName(cat, kw, pageable);
	}

}

