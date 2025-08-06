package com.example.game_management.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.game_management.model.Game;
import com.example.game_management.model.GameName;
import com.example.game_management.repository.GameRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final GameRepository gameRepository;

    public DataInitializer(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void run(String... args) {
        if (gameRepository.count() == 0) {
            List<Game> games = Arrays.asList(
                createGame("UNCHARTED4", "ADVENTURE", "Uncharted 4", "언차티드 4", "アンチャーテッド4"),
                createGame("ZELDA", "ADVENTURE", "The Legend of Zelda", "젤다의 전설", "ゼルダの伝説"),
                createGame("GOW", "ACTION", "God of War", "갓 오브 워", "ゴッド・オブ・ウォー"),
                createGame("FFX", "RPG", "Final Fantasy X", "파이널 판타지 X", "ファイナルファンタジーX"),
                createGame("MHW", "RPG", "Monster Hunter: World", "몬스터 헌터 월드", "モンスターハンター：ワールド"),
                createGame("ACODYSSEY", "ADVENTURE", "AC: Odyssey", "어쌔신 크리드 오디세이", "アサシン クリード オデッセイ"),
                createGame("RE4", "ACTION", "Resident Evil 4", "바이오하자드 4", "バイオハザード4")
            );

            gameRepository.saveAll(games);
            System.out.println("✔ 7 sample games inserted!");
        }
    }

    private Game createGame(String id, String category, String en, String ko, String ja) {
        Game game = new Game();
        game.setId(id);
        game.setCategory(category);

        GameName nameEN = new GameName("EN", en, game);
        GameName nameKO = new GameName("KO", ko, game);
        GameName nameJA = new GameName("JA", ja, game);

        game.setNameList(Arrays.asList(nameEN, nameKO, nameJA));
        return game;
    }
}
