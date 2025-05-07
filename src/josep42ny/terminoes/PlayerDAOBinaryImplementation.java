package josep42ny.terminoes;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDAOBinaryImplementation implements PlayerDAO {

    private final String[] SAVE_PATHS = new String[]{"spanish.bin", "mexican.bin", "latino.bin", "colombian.bin", "chilean.bin", "venezuelan.bin", "ponceno.bin"};
    private final String[] SAVE_NAMES = new String[]{"Espanyol", "Mexicà", "Llatinoamericà", "Colombià", "Chilè", "Veneçolà", "Ponce"};

    @Override
    public void saveAll(Player[] players, int gameType) throws DAOException {
        FileOutputStream fos;
        ObjectOutputStream oos;
        try {
            String filename = SAVE_PATHS[gameType];
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
    public Map<String, Player[]> loadAll() throws DAOException {
        Map<String, Player[]> saves = new HashMap<>();

        int index = 0;
        for (String fileName : SAVE_PATHS) {
            List<Player> out = new ArrayList<>();
            try {
                FileInputStream fin = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fin);
                Object obj;
                while (true) {
                    try {
                        obj = ois.readObject();
                        if (obj instanceof Player) {
                            out.add((Player) obj);
                        } else {
                            System.err.println("Unexpected object in file");
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        break;
                    }
                }
                ois.close();
                fin.close();
            } catch (IOException e) {
                out.add(null);
            }
            saves.put(SAVE_NAMES[index++], out.toArray(new Player[0]));
        }

        return saves;
    }
}
