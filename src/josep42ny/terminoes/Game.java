package josep42ny.terminoes;

import java.util.Random;

public abstract class Game {

    private InputHandler inputHandler;
    protected Random random;
    protected final Player[] players;
    protected BoneList boneyard;
    protected BoneList lfList;
    protected BoneList rgList;

    protected Game(int teamAmount, int playersInTeam) {
        inputHandler = new InputHandler();
        this.random = new Random();
        this.lfList = new BoneList();
        this.rgList = new BoneList();
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
        inputHandler.askPiece(new int[]{1, 2});

        Bone firstBone = takeFirstBone();
        this.lfList.add(firstBone);
        this.rgList.add(firstBone);

        while (true) {
            for (Player player : players) {
                new View().drawHand(player.getHand());
                int lf = lfList.get(-1).getLf();
                int rg = rgList.get(-1).getRg();
                System.out.println(player.getHand().getPlayableIndexes(lf));
                System.out.println(player.getHand().getPlayableIndexes(rg));
            }
            break;
        }
    }

    //todo: make abstract (depends on game variation)
    private final Bone takeFirstBone() {
        //spanish style
        int MAX_DOUBLE = 6;
        for (int i = 0; i < MAX_DOUBLE; i++) {
            for (Player player : players) {
                if (player.getHand().hasBone(i, i)) {
                    return player.getHand().takeBoneByValue(i, i);
                }
            }
        }
        return players[random.nextInt(players.length)].getHand().takeRandom(1).get(0);
    }

}
