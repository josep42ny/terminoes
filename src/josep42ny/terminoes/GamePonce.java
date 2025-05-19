package josep42ny.terminoes;

public class GamePonce extends Game {

    public GamePonce(int gameType) {
        super(gameType);
    }

    @Override
    protected int establishGameWinner() {
        int winner = 0;
        int[] teams = new int[teamAmount];
        for (Player player : players) {
            teams[player.getTeam()] += player.getScore();
            if (teams[player.getTeam()] >= MAX_SCORE) {
                winner = player.getTeam();
            }
        }
        return winner;
    }

    @Override
    protected void handleTranca(Player trancaResponsible) {
        trancaResponsible.addScore(2);

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
    protected void handlePass(int currentPlayerIndex) {
        int previousPlayerIndex = --currentPlayerIndex % players.length;
        if (firstPass) {
            players[previousPlayerIndex].addScore(2);
            firstPass = false;
        } else {
            players[previousPlayerIndex].addScore(1);
        }
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
