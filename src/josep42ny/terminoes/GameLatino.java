package josep42ny.terminoes;

public class GameLatino extends Game {

    public GameLatino(int teamAmount, int playersInTeam) {
        super(teamAmount, playersInTeam);
    }

    public GameLatino(int players) {
        this(players, 1);
    }

    @Override
    protected void distributeBones() {

    }

    @Override
    protected boolean maxScoreReached() {
        return false;
    }

    @Override
    protected void establishWinner() {

    }

    @Override
    protected Bone takeFirstBone() {
        return null;
    }

}
