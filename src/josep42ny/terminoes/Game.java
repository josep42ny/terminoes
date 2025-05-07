package josep42ny.terminoes;

import java.io.Serializable;
import java.util.Random;

public abstract class Game implements Serializable {

    transient private final GameDAO gameDAO = new GameDAOFactory().create();
    // todo make abstract these clases to get rid of pain in the ass references
    private InputHandler inputHandler;
    private View view;
    // ;
    protected Random random;
    protected Player[] players;
    protected BoneList boneyard;
    protected Board board;

    public Game() {
    }

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

    public void startGame() {
        gameLoop();
    }

    public void resumeGame() {
        playRound();
        if (maxScoreReached()) {
            establishWinner();
            return;
        }
        gameLoop();
    }

    public void gameLoop() {
        while (true) {
            distributeBones();
            placeFirstBone();
            playRound();
            if (maxScoreReached()) {
                establishWinner();
                return;
            }
        }
    }

    public final void playRound() {


        while (true) {
            for (Player player : players) {

                //
                Ansi.clearScreen();

                int[] handConstraints = player.getPlayableIndexes(board.getEnds());
                player.highlight(handConstraints);

                view.drawBoard(board);
                view.drawHand(player);

                int handIndex = inputHandler.askConstrainedInt(handConstraints, "Sel·lecciona una peça: ");
                player.unHighlightAll();

                //
                Ansi.clearScreen();

                int[] boardConstraints = board.getPlayableIndexes(player.getBone(handIndex));
                board.highlight(boardConstraints);

                view.drawBoard(board);
                view.drawHand(player);

                int boardIndex = inputHandler.askConstrainedInt(boardConstraints, "Sel·lecciona una peça: ");
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
        gameDAO.saveAll(this, 0);
    }

    protected abstract void distributeBones();

    protected abstract boolean maxScoreReached();

    protected abstract void establishWinner();

    protected abstract void placeFirstBone();

}
