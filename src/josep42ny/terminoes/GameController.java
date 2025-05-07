package josep42ny.terminoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    private final View view;
    private final InputHandler inputHandler;
    private GameDAO gameDAO;
    private final int[] GAME_INDEXES = new int[]{0, 1, 2, 3, 4, 5, 6};

    public GameController() {
        this.inputHandler = new InputHandler();
        this.view = new View();
        this.gameDAO = new GameDAOFactory().create();
    }

    public void awake() {
        Map<String, Game> saves = gameDAO.loadAll();
        boolean wantsToLoad = false;

        view.homeScreen();
        if (foundSaveIn(saves)) {
            wantsToLoad = inputHandler.askLoad();
        }

        view.homeScreen();
        if (wantsToLoad) {
            view.drawSavedGames(saves);
            int selection = inputHandler.askConstrainedInt(getSaveIndexes(saves), "Selecciona un mode [0-6]: ");
            Game save = saves.get(saves.keySet().toArray()[selection]);
            save.resumeGame();
        } else {
            view.drawNewGames();
            int selection = inputHandler.askConstrainedInt(GAME_INDEXES, "Selecciona un mode [0-6]: ");
            Game game = switch (selection) {
                case 0 -> new GameSpanish(2);
                case 1 -> new GameMexican(2);
                case 2 -> new GameLatino(2);
                case 3 -> new GameColombian(2);
                case 4 -> new GameChilean(2);
                case 5 -> new GameVenezuelan(2);
                case 6 -> new GamePonceno(2);
                default -> throw new IllegalStateException("Unexpected value: " + selection);
            };
            game.startGame();
        }
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
