package josep42ny.terminoes;

import josep42ny.terminoes.persistance.GameDAO;
import josep42ny.terminoes.persistance.GameDAOFactory;
import josep42ny.terminoes.utilities.Ansi;
import josep42ny.terminoes.utilities.InputHandler;
import josep42ny.terminoes.utilities.View;

import java.io.Serializable;
import java.util.Random;

public abstract class Game implements Serializable {

    private final GameDAO gameDAO;
    protected Random random;
    protected Player[] players;
    protected Board board;
    protected int teamAmount;
    protected int playerAmount;
    protected int maxScore;
    protected int currentPlayer;
    boolean firstRound = true;
    boolean firstPass = true;

    protected Game(int playerAmount) {
        this.gameDAO = new GameDAOFactory().create();
        this.maxScore = maxScore();
        this.random = new Random();
        this.players = new Player[4];
        this.playerAmount = playerAmount;

        if (allowSinglePlayer() && InputHandler.askBoolean("Vols jugar individualment [y/n]?")) {
            this.teamAmount = playerAmount;
        } else {
            this.teamAmount = 2;
        }

        initPlayers();
    }

    private void initPlayers() {
        int current = 0;
        int playersInTeam = playerAmount / teamAmount;
        for (int player = 0; player < playersInTeam; player++) {
            for (int teamIndex = 0; teamIndex < teamAmount; teamIndex++) {
                players[current] = new Player(teamIndex);
                current++;
            }
        }
    }

    private void initPlayerHands() {
        int BONES_PER_PLAYER = 28 / players.length;
        BoneList boneyard = new BoneList();
        boneyard.fill();
        for (Player player : players) {
            player.setHand(boneyard.takeRandom(BONES_PER_PLAYER));
        }
    }

    public void startGame() {
        gameLoop();
    }

    public void resumeGame() {
        playRound();
        if (maxScoreReached()) {
            View.displayWinner(establishGameWinner());
            return;
        }
        gameLoop();
    }

    public void gameLoop() {
        while (true) {
            initPlayerHands();
            if (firstRound) {
                currentPlayer = playFirstRoundStarter();
                firstRound = false;
            } else {
                currentPlayer = playNextRoundStarter();
            }
            nextPlayer();
            playRound();
            if (maxScoreReached()) {
                View.displayWinner(establishGameWinner());
                return;
            }
        }
    }

    public final void playRound() {
        while (true) {
            Player player = players[currentPlayer];
            int[] boardEnds = board.getEnds();

            View.drawBoard(board);
            View.drawHand(player);

            if (!player.canPlay(boardEnds)) {
                handlePass();
                playerSwap();
                continue;
            }

            //
            Ansi.clearScreen();

            int[] handConstraints = player.getPlayableIndexes(boardEnds);

            player.highlight(handConstraints);
            View.drawBoard(board);
            View.drawHand(player);

            int handIndex = InputHandler.askConstrainedInt(handConstraints, "Selecciona una peça: ");
            player.unHighlightAll();

            //
            Ansi.clearScreen();

            int[] boardConstraints = board.getPlayableIndexes(player.getBone(handIndex));

            board.highlight(boardConstraints);
            View.drawBoard(board);
            View.drawHand(player);

            int boardIndex = InputHandler.askConstrainedInt(boardConstraints, "Selecciona una peça: ");
            board.unHighlightAll();

            //
            board.add(player.takeBone(handIndex), boardIndex);
            Ansi.clearScreen();
            View.drawBoard(board);
            View.drawHand(player);

            if (noOneCanPlay()) {
                handleTranca();
                return;
            }

            if (player.getHand().isEmpty()) {
                handleRoundWinner();
                return;
            }

            playerSwap();
        }
    }

    private boolean noOneCanPlay() {
        for (Player player : players) {
            if (player.canPlay(board.getEnds())) {
                return false;
            }
        }
        return true;
    }

    private void playerSwap() {
        InputHandler.waitKeyPress();
        InputHandler.waitPlayerSwap();
        nextPlayer();
        gameDAO.saveAll(this, 0);
    }

    private void nextPlayer() {
        currentPlayer = ++currentPlayer % players.length;
    }

    private boolean maxScoreReached() {
        int[] teams = new int[teamAmount];
        for (Player player : players) {
            teams[player.getTeam()] += player.getScore();
            if (teams[player.getTeam()] >= maxScore) {
                return true;
            }
        }
        return false;
    }

    protected abstract int establishGameWinner();

    protected abstract void handleTranca();

    protected abstract void handlePass();

    protected abstract void handleRoundWinner();

    // returns the index of the player the bone is taken from
    protected abstract int playNextRoundStarter();

    protected abstract int playFirstRoundStarter();

    protected abstract boolean allowSinglePlayer();

    protected abstract int maxScore();

}
