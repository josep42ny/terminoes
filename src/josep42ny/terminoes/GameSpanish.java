package josep42ny.terminoes;

public class GameSpanish extends Game {

    public GameSpanish(int players) {
        super(players);
    }

    @Override
    protected int establishGameWinner() {
        int winner = 0;
        int[] teams = new int[teamAmount];
        for (Player player : players) {
            teams[player.getTeam()] += player.getScore();
            if (teams[player.getTeam()] >= maxScore) {
                winner = player.getTeam();
            }
        }
        return winner;
    }

    @Override
    protected int placeFirstBone() {
        Bone bone;

        int MAX_DOUBLE = 6;
        for (int i = MAX_DOUBLE; i >= 0; i--) {
            for (int index = 0; index < players.length; index++) {
                if (players[index].hasBone(i, i)) {
                    bone = players[index].takeBoneByValue(i, i);
                    board.setCenter(bone);
                    return index;
                }
            }
        }

        int randIndex = random.nextInt(players.length);
        bone = players[randIndex].takeRandom(1).get(0);
        board.setCenter(bone);
        return randIndex;
    }

    @Override
    protected void handleTanca() {
        Player winner = players[0];
        int lowestPoints = players[0].getHandPoints();
        int allHandsPoints = 0;
        for (int i = 1; i < players.length; i++) {
            int playerPoints = players[i].getHandPoints();
            allHandsPoints += playerPoints;
            if (playerPoints < lowestPoints) {
                winner = players[i];
                lowestPoints = playerPoints;
            }
        }
        winner.addScore(allHandsPoints);
    }

    @Override
    protected void handleRoundWinner() {
        Player winner = players[current];
        int roundPoints = 0;
        for (Player player : players) {
            roundPoints += player.getHandPoints();
        }
        roundPoints -= winner.getHandPoints();
        winner.addScore(roundPoints);
    }

    @Override
    protected void handlePass() {

    }

    @Override
    protected boolean allowSingleplayer() {
        return true;
    }

    @Override
    protected int maxScore() {
        return 200;
    }
}
