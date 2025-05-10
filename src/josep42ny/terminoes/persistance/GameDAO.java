package josep42ny.terminoes.persistance;

import josep42ny.terminoes.Game;

import java.util.Map;

public interface GameDAO {

    void saveAll(Game game, int gameType);

    Map<String, Game> loadAll();

    void delete(String saveName);

}
