package josep42ny.terminoes;

public class GameMexican extends Game {

    public GameMexican(int gameType) {
        super(gameType);
    }

    @Override
    protected int getFirstRoundStartingPlayer() {
        return getNormalRoundStartingPlayer(-1);
    }

    @Override
    protected int getNormalRoundStartingPlayer(int previousWinnerIndex) {
        int maxBoneValue = 0;
        int maxBoneIndex = 0;
        int startingPlayerIndex = 0;

        for (int i = 0; i < players.length; i++) {
            Player curPlayer = players[i];
            int handSize = curPlayer.getHand().size();
            int randomBoneIndex = random.nextInt(handSize);
            int randomBoneValue = curPlayer.getBone(randomBoneIndex).getValue();
            if (randomBoneValue > maxBoneValue) {
                maxBoneValue = randomBoneValue;
                maxBoneIndex = randomBoneIndex;
                startingPlayerIndex = i;
            }
        }

        Bone bone = players[startingPlayerIndex].takeBone(maxBoneIndex);
        bone.setDirection(Direction.LF);
        board = new Board(bone);
        return startingPlayerIndex;
    }

    @Override
    protected int establishGameWinner() {
        int[] teams = new int[teamAmount];
        int winner = 0;
        for (Player player : players) {
            teams[player.getTeam()] += player.getScore();
            if (teams[player.getTeam()] >= maxScore()) {
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
        int totalPoints = 0;
        for (int team = 0; team < teamAmount; team++) {
            totalPoints += teamPoints[team];
            if (teamPoints[team] < teamPoints[winningTeam]) {
                winningTeam = team;
            }
        }

        for (Player player : players) {
            if (player.getTeam() == winningTeam) {
                // Only one player on the winning team gets the points
                totalPoints -= player.getHandPoints();
                player.addScore(totalPoints);
                return;
            }
        }
    }

    @Override
    protected void handlePass(int currentPlayerIndex) {

    }

    @Override
    protected void scoreRoundWinner(Player winner) {
        int roundPoints = 0;
        for (Player player : players) {
            roundPoints += player.getHandPoints();
        }
        roundPoints -= winner.getHandPoints();
        winner.addScore(roundPoints);
    }

    @Override
    protected boolean allowSinglePlayer() {
        return true;
    }

    @Override
    protected int maxScore() {
        return 300;
        // or return 200;
    }
}
