package josep42ny.terminoes;

public class GameChilean extends Game {

    public GameChilean(int teamAmount, int playersInTeam) {
        super(teamAmount, playersInTeam);
    }

    public GameChilean(int players) {
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
