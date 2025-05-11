package josep42ny.terminoes;

public class GamePonce extends Game {

    public GamePonce(int players) {
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
    protected void handleTanca() {
        players[currentPlayer].addScore(2);

        int min = 0;
        int total = players[min].getScore();
        for (int i = 1; i < players.length; i++) {
            total += players[i].getScore();
            if (players[i].getScore() < players[min].getScore()) {
                min = i;
            }
        }
        players[min].addScore(total);

        for (Player player : players) {
            int rounded = (int) (Math.round(player.getScore() / 10.0) * 10);
            player.setScore(rounded);
        }
    }

    @Override
    protected void handlePass() {
        int previous = --currentPlayer % players.length;
        if (firstPass) {
            players[previous].addScore(2);
            firstPass = false;
        } else {
            players[previous].addScore(1);
        }
    }

    @Override
    protected int playFirstRoundStarter() {
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
    protected int playNextRoundStarter() {
        int next = ++currentPlayer % players.length;
        Bone bone = players[next].takeBiggest();
        board.setCenter(bone);
        return next;
    }

    @Override
    protected void handleRoundWinner() {
        for (Player player : players) {
            int rounded = (int) (Math.round(player.getScore() / 10.0) * 10);
            player.setScore(rounded);
        }
    }

    @Override
    protected boolean allowSinglePlayer() {
        return false;
    }

    @Override
    protected int maxScore() {
        return 20;
    }
}
