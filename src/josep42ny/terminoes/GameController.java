package josep42ny.terminoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    private final View view;
    private final InputHandler inputHandler;
    private PlayerDAO playerDAO;
    private final int[] GAME_INDEXES = new int[]{0, 1, 2, 3, 4, 5, 6};

    public GameController() {
        this.inputHandler = new InputHandler();
        this.view = new View();
        this.playerDAO = new PlayerDAOFactory().create();
    }

    public void awake() {

        Map<String, Player[]> saves = playerDAO.loadAll();
        boolean wantsToLoad = false;

        view.homeScreen();
        if (foundSaveIn(saves)) {
            wantsToLoad = inputHandler.askLoad();
        }
        view.homeScreen();
        if (wantsToLoad) {
            view.drawSavedGames(saves);
            int selection = inputHandler.askConstrainedInt(getSaveIndexes(saves), "Sel·lecciona un mode [0-6]: ");
            start(selection);
        } else {
            view.drawNewGames();
            int selection = inputHandler.askConstrainedInt(GAME_INDEXES, "Sel·lecciona un mode [0-6]: ");
            start(selection);
        }
    }

    private void start(int selection) {
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
        game.gameLoop();
    }

    private boolean foundSaveIn(Map<String, Player[]> saves) {
        for (Map.Entry<String, Player[]> entry : saves.entrySet()) {
            if (entry.getValue()[0] != null) {
                return true;
            }
        }
        return false;
    }

    private int[] getSaveIndexes(Map<String, Player[]> saves) {
        List<Integer> indexes = new ArrayList<>();
        int index = 0;
        for (Map.Entry<String, Player[]> entry : saves.entrySet()) {
            if (entry.getValue()[0] != null) {
                indexes.add(index);
            }
            index++;
        }
        return indexes.stream().mapToInt(i -> i).toArray();
    }

}
