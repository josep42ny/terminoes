package josep42ny.terminoes.persistance;

public class GameDAOFactory {

    public GameDAO create() {
        return new GameDAOBinaryImplementation();
    }

}
