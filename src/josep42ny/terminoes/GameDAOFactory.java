package josep42ny.terminoes;

public class GameDAOFactory {

    public GameDAO create() {
        return new GameDAOBinaryImplementation();
    }

}
