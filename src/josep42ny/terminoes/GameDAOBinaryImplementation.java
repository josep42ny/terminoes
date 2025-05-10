package josep42ny.terminoes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GameDAOBinaryImplementation implements GameDAO, Serializable {

    private final String[] SAVE_PATHS = new String[]{"spanish.bin", "mexican.bin", "latino.bin", "colombian.bin", "chilean.bin", "venezuelan.bin", "ponce.bin"};
    private final String[] SAVE_NAMES = new String[]{"Espanyol", "Mexicà", "Llatinoamericà", "Colombià", "Chilè", "Veneçolà", "Ponce"};

    @Override
    public void saveAll(Game game, int gameType) throws DAOException {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            String filename = SAVE_PATHS[gameType];
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(game);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new DAOException("Error finding employee resource", e);
        }
    }

    @Override
    public Map<String, Game> loadAll() throws DAOException {
        Map<String, Game> saves = new HashMap<>();

        int index = 0;
        for (String fileName : SAVE_PATHS) {
            Game out = null;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                Object obj = ois.readObject();
                if (obj instanceof Game) {
                    out = (Game) obj;
                } else {
                    System.err.println("Unexpected object in file");
                }
            } catch (IOException | ClassNotFoundException ignored) {
            }
            saves.put(SAVE_NAMES[index++], out);
        }

        return saves;
    }

    @Override
    public void delete(String saveName) {
        File file = new File(saveName);
        file.delete();
    }
}
