package josep42ny.terminoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    private GameDAO gameDAO;
    private final int[] GAME_INDEXES = new int[]{0, 1, 2, 3, 4, 5, 6};

    public GameController() {
        this.gameDAO = new GameDAOFactory().create();
    }

    public void awake() {
        int winnerId;
        Map<String, Game> saves = gameDAO.loadAll();
        boolean wantsToLoad = false;

        View.homeScreen();
        if (foundSaveIn(saves)) {
            wantsToLoad = InputHandler.askLoad();
        }

        View.homeScreen();
        if (wantsToLoad) {
            View.drawSavedGames(saves);
            int selection = InputHandler.askConstrainedInt(getSaveIndexes(saves), "Selecciona un mode [0-6]: ");
            String saveName = saves.keySet().toArray()[selection].toString();
            Game game = saves.get(saveName);
            Ansi.clearScreen();
            winnerId = game.resumeGame();
            gameDAO.delete(saveName);
        } else {
            View.drawNewGames();
            int selection = InputHandler.askConstrainedInt(GAME_INDEXES, "Selecciona un mode [0-6]: ");
            Game game = switch (selection) {
                case 0 -> new GameSpanish(4);
                case 1 -> new GameMexican(4);
                case 2 -> new GameLatino(4);
                case 3 -> new GameColombian(4);
                case 4 -> new GameChilean(4);
                case 5 -> new GameVenezuelan(4);
                case 6 -> new GamePonceno(4);
                default -> throw new IllegalStateException("Unexpected value: " + selection);
            };
            Ansi.clearScreen();
            winnerId = game.startGame();
        }

        View.displayWinner(winnerId);
    }


    private boolean foundSaveIn(Map<String, Game> saves) {
        for (Map.Entry<String, Game> entry : saves.entrySet()) {
            if (entry.getValue() != null) {
                return true;
            }
        }
        return false;
    }

    private int[] getSaveIndexes(Map<String, Game> saves) {
        List<Integer> indexes = new ArrayList<>();
        int index = 0;
        for (Map.Entry<String, Game> entry : saves.entrySet()) {
            if (entry.getValue() != null) {
                indexes.add(index);
            }
            index++;
        }
        return indexes.stream().mapToInt(i -> i).toArray();
    }

}
