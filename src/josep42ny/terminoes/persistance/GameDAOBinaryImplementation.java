package josep42ny.terminoes.persistance;

import josep42ny.terminoes.Game;

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
            throw new DAOException("Error saving the game to file: " + SAVE_PATHS[gameType], e);
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
        File save = new File(saveName);
        if (save.delete()) {
            System.out.println("Deleted the file: " + save.getName());
        } else {
            System.out.println("Failed to delete the file: " + save.getName());
        }
    }
}
