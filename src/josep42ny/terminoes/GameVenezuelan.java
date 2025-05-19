package josep42ny.terminoes;

public class GameVenezuelan extends Game {

    public GameVenezuelan(int gameType) {
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
        int[] teamPoints = new int[teamAmount];

        // Count hand points per team
        for (Player player : players) {
            teamPoints[player.getTeam()] += player.getHandPoints();
        }

        // Find team with the lowest hand points
        int winningTeam = 0;
        for (int team = 0; team < teamAmount; team++) {
            if (teamPoints[team] < teamPoints[winningTeam]) {
                winningTeam = team;
            }
        }

        // Sum points of losing teams
        int totalPoints = 0;
        for (int i = 0; i < teamPoints.length; i++) {
            if (i != winningTeam) {
                totalPoints += teamPoints[i];
            }
        }

        for (Player player : players) {
            if (player.getTeam() == winningTeam) {
                // Only one player on the winning team gets the points
                player.addScore(totalPoints);
                return;
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
        int winnerTeam = winner.getTeam();
        for (Player player : players) {
            if (player.getTeam() != winnerTeam) {
                winner.addScore(player.getHandPoints());
            }
        }
    }

    @Override
    protected boolean allowSinglePlayer() {
        return false;
    }

    @Override
    protected int maxScore() {
        return 75;
        //or return 100;
    }
}
