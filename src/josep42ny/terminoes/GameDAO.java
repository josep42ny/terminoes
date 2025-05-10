package josep42ny.terminoes;

import java.util.Map;

public interface GameDAO {

    void saveAll(Game game, int gameType);

    Map<String, Game> loadAll();

    void delete(String saveName);

}
