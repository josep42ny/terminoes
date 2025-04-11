package josep42ny.terminoes;

public abstract class Game {

    protected final Player[] players;
    protected BoneList boneyard;

    protected Game(int teamAmount, int playersInTeam) {
        this.boneyard = new BoneList();
        this.boneyard.fill();
        this.players = new Player[teamAmount * playersInTeam];
        int current = 0;
        for (int player = 0; player < playersInTeam; player++) {
            for (int teamIndex = 0; teamIndex < teamAmount; teamIndex++) {
                players[current] = new Player(teamIndex);
                current++;
            }
        }
        int BONES_PER_PLAYER = 28 / (teamAmount * playersInTeam);
        for (Player player : this.players) {
            player.setHand(boneyard.takeRandom(BONES_PER_PLAYER));
        }
    }

    public final void playRound() {

    }

    public void test() {
        for (Player player : players) {
            new View().drawHand(player.getHand());
        }
    }

}
