package josep42ny.terminoes;

public class GameController {

    public void awake() {
        Game game = new GameSpanish(2);
        game.gameLoop();
    }

}
