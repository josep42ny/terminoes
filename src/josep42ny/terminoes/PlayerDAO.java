package josep42ny.terminoes;

import java.util.Map;

public interface PlayerDAO {

    void saveAll(Player[] players, int gameType);

    Map<String, Player[]> loadAll();

}
