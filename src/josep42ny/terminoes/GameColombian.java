package josep42ny.terminoes;

public class GameColombian extends Game {

    public GameColombian(int players) {
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
        int winnerTeam = players[current].getTeam();
        for (Player player : players) {
            if (player.getTeam() != winnerTeam) {
                players[current].addScore(player.getHandPoints());
            }
        }
    }

    @Override
    protected boolean allowSingleplayer() {
        return false;
    }

    @Override
    protected int maxScore() {
        return 100;
    }
}
