package josep42ny.terminoes;

import josep42ny.terminoes.persistance.GameDAO;
import josep42ny.terminoes.persistance.GameDAOFactory;
import josep42ny.terminoes.utilities.Ansi;
import josep42ny.terminoes.utilities.InputHandler;
import josep42ny.terminoes.utilities.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameLoader {
    private static final int MAX_GAME_MODES = 7;
    private final GameDAO gameDAO;
    private final int[] GAME_INDEXES = new int[]{0, 1, 2, 3, 4, 5, 6};

    public GameLoader() {
        this.gameDAO = new GameDAOFactory().create();
    }

    public void awake() {
        View.homeScreen();
        Map<String, Game> savedGames = gameDAO.loadAll();
        boolean shouldLoadSavedGame = hasSavedGames(savedGames) && InputHandler.askLoad();
        View.homeScreen();

        if (shouldLoadSavedGame) {
            loadSavedGame(savedGames);
        } else {
            startNewGame();
        }
    }

    private boolean hasSavedGames(Map<String, Game> savedGames) {
        for (Map.Entry<String, Game> entry : savedGames.entrySet()) {
            if (entry.getValue() != null) {
                return true;
            }
        }
        return false;
    }

    private int[] extractSavedGameIndexes(Map<String, Game> savedGames) {
        List<Integer> indexes = new ArrayList<>();
        int index = 0;
        for (Map.Entry<String, Game> entry : savedGames.entrySet()) {
            if (entry.getValue() != null) {
                indexes.add(index);
            }
            index++;
        }
        return indexes.stream().mapToInt(i -> i).toArray();
    }

    private void loadSavedGame(Map<String, Game> savedGames) {
        View.drawSavedGames(savedGames);
        int selection = InputHandler.askConstrainedInt(extractSavedGameIndexes(savedGames), "Selecciona un mode [0-" + (MAX_GAME_MODES - 1) + "]: ");
        String saveName = (String) savedGames.keySet().toArray()[selection];
        Game game = savedGames.get(saveName);

        Ansi.clearScreen();
        game.resumeGame();
        gameDAO.delete(saveName);
    }

    private void startNewGame() {
        View.drawNewGames();
        int selection = InputHandler.askConstrainedInt(GAME_INDEXES, "Selecciona un mode [0-" + (MAX_GAME_MODES - 1) + "]: ");
        Game game = switch (selection) {
            case 0 -> new GameSpanish(0);
            case 1 -> new GameMexican(1);
            case 2 -> new GameLatino(2);
            case 3 -> new GameColombian(3);
            case 4 -> new GameChilean(4);
            case 5 -> new GameVenezuelan(5);
            case 6 -> new GamePonce(6);
            default -> throw new IllegalStateException("Unexpected value: " + selection);
        };

        Ansi.clearScreen();
        game.startGame();
    }
}