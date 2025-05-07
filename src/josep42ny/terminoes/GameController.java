package josep42ny.terminoes;

public class GameController {

    private final View view;
    private final InputHandler inputHandler;
    private final int[] GAME_INDEXES = new int[] {0,1,2,3,4,5,6};

    public GameController() {
        this.inputHandler = new InputHandler();
        this.view = new View();
    }

    public void awake() {

        view.homeScreen();
        int selection = inputHandler.askConstrainedInt(GAME_INDEXES, "Sel·lecciona un mode [0-6]: ");

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

}
