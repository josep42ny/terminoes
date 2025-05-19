package josep42ny.terminoes;

public class GameChilean extends Game {

    public GameChilean(int gameType) {
        super(gameType);
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
    protected void handleTranca(Player trancaResponsible) {
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
    protected void handlePass(int currentPlayerIndex) {

    }

    @Override
    protected int getFirstRoundStartingPlayer() {
        Bone bone;

        int MAX_DOUBLE = 6;
        for (int i = MAX_DOUBLE; i >= 0; i--) {
            for (int index = 0; index < players.length; index++) {
                if (players[index].hasBone(i, i)) {
                    bone = players[index].takeBoneByValue(i, i);
                    board = new Board(bone);
                    return index;
                }
            }
        }

        int randIndex = random.nextInt(players.length);
        bone = players[randIndex].takeRandom(1).get(0);
        board = new Board(bone);
        return randIndex;
    }

    @Override
    protected int getNormalRoundStartingPlayer(int previousWinnerIndex) {
        int startingPlayerIndex = getNextPlayerOf(previousWinnerIndex);
        Bone bone = players[startingPlayerIndex].takeBiggest();
        board = new Board(bone);
        return startingPlayerIndex;
    }

    @Override
    protected void scoreRoundWinner(Player winner) {
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
