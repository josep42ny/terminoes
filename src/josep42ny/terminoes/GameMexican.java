package josep42ny.terminoes;

public class GameMexican extends Game {

    public GameMexican(int teamAmount, int playersInTeam) {
        super(teamAmount, playersInTeam);
    }

    public GameMexican(int players) {
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
    protected void placeFirstBone() {
    }

}
