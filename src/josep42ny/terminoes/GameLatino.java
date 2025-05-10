package josep42ny.terminoes;

public class GameLatino extends Game {

    public GameLatino(int players) {
        super(players);
    }

    @Override
    protected int establishGameWinner() {
        return 0;
    }

    @Override
    protected void handleTanca() {

    }

    @Override
    protected void handlePass() {

    }

    @Override
    protected int placeFirstBone() {
        return 0;
    }

    @Override
    protected void handleRoundWinner() {

    }

    @Override
    protected boolean allowSingleplayer() {
        return false;
    }

    @Override
    protected int maxScore() {
        return 200;
        // or return 100;
    }
}
