package josep42ny.terminoes;

import java.util.Random;

public abstract class Game {

    private final PlayerDAOFactory playerDAOFactory = new PlayerDAOFactory();
    private PlayerDAO playerDAO = playerDAOFactory.create();
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
    }

    public void gameLoop() {
        while (true) {
            distributeBones();
            playRound();
            if (maxScoreReached()) {
                establishWinner();
                return;
            }
        }
    }

    public final void playRound() {

        board.setCenter(takeFirstBone());

        while (true) {
            for (Player player : players) {

                //
                Ansi.clearScreen();

                int[] handConstraints = player.getPlayableIndexes(board.getEnds());
                player.highlight(handConstraints);

                view.drawBoard(board);
                view.drawHand(player);

                int handIndex = inputHandler.askPiece(handConstraints);
                player.unHighlightAll();

                //
                Ansi.clearScreen();

                int[] boardConstraints = board.getPlayableIndexes(player.getBone(handIndex));
                board.highlight(boardConstraints);

                view.drawBoard(board);
                view.drawHand(player);

                int boardIndex = inputHandler.askPiece(boardConstraints);
                board.unHighlightAll();

                //
                board.add(player.takeBone(handIndex), boardIndex);
                Ansi.clearScreen();
                view.drawBoard(board);
                view.drawHand(player);
                playerSwapTransition();
            }
        }
    }

    private void playerSwapTransition() {
        inputHandler.waitKeyPress();
        inputHandler.waitPlayerSwap();
        playerDAO.saveAll(players);
    }

    protected abstract void distributeBones();

    protected abstract boolean maxScoreReached();

    protected abstract void establishWinner();

    protected abstract Bone takeFirstBone();

}
