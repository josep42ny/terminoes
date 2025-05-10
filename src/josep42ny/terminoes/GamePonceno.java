package josep42ny.terminoes;

public class GamePonceno extends Game {

    public GamePonceno(int players) {
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
        return 20;
    }
}
