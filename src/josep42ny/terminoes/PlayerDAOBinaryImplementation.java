package josep42ny.terminoes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PlayerDAOBinaryImplementation implements PlayerDAO {

    private final String FILENAME = "save.bin";

    @Override
    public void saveAll(Player[] players) throws DAOException {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            fos = new FileOutputStream(FILENAME);
            oos = new ObjectOutputStream(fos);
            for (Player player : players) {
                oos.writeObject(player);
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new DAOException("Error finding employee resource", e);
        }
    }

    // todo
    @Override
    public Player[] loadAll() throws DAOException {
        return new Player[0];
    }
}
