package josep42ny.terminoes;

public class GameSpanish extends Game {

    public GameSpanish(int teams, int playersInTeam) {
        super(teams, playersInTeam);
    }

    public GameSpanish(int players) {
        this(players, 1);
    }

    public GameSpanish() {

    }

    @Override
    protected void distributeBones() {
        int BONES_PER_PLAYER = 28 / players.length;
        for (Player player : players) {
            player.setHand(boneyard.takeRandom(BONES_PER_PLAYER));
        }
    }

    @Override
    protected boolean maxScoreReached() {
        return false;
    }

    @Override
    protected void establishWinner() {

    }

    @Override
    protected void placeFirstBone() {
        Bone bone;
        int MAX_DOUBLE = 6;
        for (int i = MAX_DOUBLE; i >= 0; i--) {
            for (Player player : players) {
                if (player.getHand().hasBone(i, i)) {
                    bone = player.getHand().takeBoneByValue(i, i);
                }
            }
        }
        bone = players[random.nextInt(players.length)].getHand().takeRandom(1).take(0);
        board.setCenter(bone);
    }
}
