package josep42ny.terminoes;

public class GameController {

    public void awake() {
        Game game = new GameMexican(2);
        game.playRound();
    }

}
