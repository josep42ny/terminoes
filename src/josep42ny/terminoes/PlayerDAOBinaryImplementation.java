package josep42ny.terminoes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PlayerDAOBinaryImplementation implements PlayerDAO {

    private final String[] SAVE_NAMES = new String[] {"spanish.bin","mexican.bin","latino.bin","colombian.bin","chilean.bin","venezuelan.bin","ponceno.bin"};

    @Override
    public void saveAll(Player[] players, int gameType) throws DAOException {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            String filename = SAVE_NAMES[gameType];
            fos = new FileOutputStream(filename);
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

    @Override
    public Player[] loadAll() throws DAOException {
        for (String fileName : SAVE_NAMES) {
            // todo try to read files if they exist
        }
        return new Player[0];
    }
}
