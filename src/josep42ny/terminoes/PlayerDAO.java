package josep42ny.terminoes;

public interface PlayerDAO {

    void saveAll(Player[] players);

    Player[] loadAll();

}
