package josep42ny.terminoes;

public class PlayerDAOFactory {

    public PlayerDAO create() {
        return new PlayerDAOBinaryImplementation();
    }

}
