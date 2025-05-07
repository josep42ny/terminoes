package josep42ny.terminoes;

public interface PlayerDAO {

    void saveAll(Player[] players, int gameType);

    Player[] loadAll();

}
