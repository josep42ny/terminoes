package josep42ny.terminoes;

public class GameMexican extends Game {

    public GameMexican(int teamAmount, int playersInTeam) {
        super(teamAmount, playersInTeam);
    }

    public GameMexican(int players) {
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
