package josep42ny.terminoes;

public class GameChilean extends Game {

    public GameChilean(int players) {
        super(players);
    }

    @Override
    protected int establishGameWinner() {
        int[] teams = new int[teamAmount];
        for (Player player : players) {
            teams[player.getTeam()] += player.getScore();
        }

        int winner = 0;
        for (int i = 1; i < teams.length; i++) {
            if (teams[i] < teams[winner]) {
                winner = i;
            }
        }

        return winner;
    }

    @Override
    protected void handleTranca() {
        int[] teams = new int[teamAmount];

        int totalPoints = 0;
        for (Player player : players) {
            totalPoints += player.getScore();
            teams[player.getTeam()] += player.getScore();
        }

        int loser = 0;
        for (int i = 1; i < teams.length; i++) {
            if (teams[i] > teams[loser]) {
                loser = i;
            }
        }

        for (Player player : players) {
            if (player.getTeam() == loser) {
                player.addScore(totalPoints);
            }
        }
    }

    @Override
    protected void handlePass() {

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
            player.addScore(player.getHandPoints());
        }
    }

    @Override
    protected boolean allowSinglePlayer() {
        return true;
    }

    @Override
    protected int maxScore() {
        return 121;
    }
}
