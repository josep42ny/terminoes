package josep42ny.terminoes;

public class GameColombian extends Game {

    public GameColombian(int teamAmount, int playersInTeam) {
        super(teamAmount, playersInTeam);
    }

    public GameColombian(int players) {
        this(players, 1);
    }

    @Override
    protected int establishWinner() {
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

}
