package josep42ny.terminoes;

public class GameLatino extends Game {

    public GameLatino(int players) {
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
    protected void handlePass() {
        players[currentPlayer].subScore(25);
        players[(currentPlayer + 1) % players.length].addScore(25);
    }

    @Override
    protected int playFirstRoundStarter() {
        return playNextRoundStarter();
    }

    @Override
    protected int playNextRoundStarter() {
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
    protected void handleRoundWinner() {
        int winnerTeam = players[currentPlayer].getTeam();
        for (Player player : players) {
            if (player.getTeam() != winnerTeam) {
                players[currentPlayer].addScore(player.getHandPoints());
            }
        }
    }

    @Override
    protected boolean allowSingleplayer() {
        return false;
    }

    @Override
    protected int maxScore() {
        return 200;
        // or return 100;
    }
}
