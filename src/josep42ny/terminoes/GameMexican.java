package josep42ny.terminoes;

public class GameMexican extends Game {

    public GameMexican(int players) {
        super(players);
    }

    @Override
    protected int placeFirstBone() {
        int maxBoneValue = 0;
        int maxBoneIndex = 0;
        int playerIndex = 0;

        for (int i = 0; i < players.length; i++) {
            Player curPlayer = players[i];
            int handSize = curPlayer.getHand().size();
            int randomBoneIndex = random.nextInt(handSize);
            int randomBoneValue = curPlayer.getBone(randomBoneIndex).getValue();
            if (randomBoneValue > maxBoneValue) {
                maxBoneValue = randomBoneValue;
                maxBoneIndex = randomBoneIndex;
                playerIndex = i;
            }
        }

        Bone bone = players[playerIndex].takeBone(maxBoneIndex);
        bone.setDirection(Direction.LF);
        board.setCenter(bone);
        return playerIndex;
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
    protected void handleTanca() {
        int[] teamPoints = new int[teamAmount];
        for (Player player : players) {
            teamPoints[player.getTeam()] += player.getHandPoints();
        }

        int winingTeam = 0;
        int minPoints = teamPoints[winingTeam];
        int totalPoints = 0;
        for (int team = 1; team < teamAmount; team++) {
            if (teamPoints[team] < minPoints) {
                totalPoints += teamPoints[team];
                minPoints = teamPoints[team];
                winingTeam = team;
            }
        }

        for (Player player : players) {
            if (player.getTeam() == winingTeam) {
                totalPoints -= player.getHandPoints();
                player.addScore(totalPoints);
                return;
            }
        }
    }

    @Override
    protected void handlePass() {

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
    protected boolean allowSingleplayer() {
        return true;
    }

    @Override
    protected int maxScore() {
        return 300;
        // or return 200;
    }
}
