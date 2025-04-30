package josep42ny.terminoes;

import java.util.Random;

public abstract class Game {

    private InputHandler inputHandler;
    private View view;
    protected Random random;
    protected final Player[] players;
    protected BoneList boneyard;
    protected Board board;

    protected Game(int teamAmount, int playersInTeam) {
        this.inputHandler = new InputHandler();
        this.view = new View();
        this.random = new Random();
        this.board = new Board();
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
        board.setCenter(takeFirstBone());

        while (true) {
            for (Player player : players) {

                //
                Ansi.clearScreen();

                int[] test = player.getPlayableIndexes(board.getEnds());
                player.highlight(test);

                view.drawBoard(board);
                view.drawHand(player);

                int handIndex = inputHandler.askPiece(test);
                player.unHighlightAll();

                //
                Ansi.clearScreen();

                int[] test2 = board.getPlayableIndexes(player.getBone(handIndex));
                board.highlight(test2);

                view.drawBoard(board);
                view.drawHand(player);

                int boardIndex = inputHandler.askPiece(test2);
                board.unHighlightAll();

                //
                board.add(player.takeBone(handIndex), boardIndex);
            }
        }
    }

    private helper(subject) {

    }

    //todo: make abstract (depends on game variation)
    private final Bone takeFirstBone() {
        //spanish style
        int MAX_DOUBLE = 6;
        for (int i = MAX_DOUBLE; i >= 0; i--) {
            for (Player player : players) {
                if (player.getHand().hasBone(i, i)) {
                    return player.getHand().takeBoneByValue(i, i);
                }
            }
        }
        return players[random.nextInt(players.length)].getHand().takeRandom(1).take(0);
    }

}
