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
    protected final Random random;
    protected final Player[] players;
    private final int GAME_TYPE;
    protected final int MAX_SCORE;
    protected Board board;
    protected int teamAmount;

    // persistence
    boolean firstRound = true;
    boolean firstPass = true;
    int savedPlayerIndex = 0;

    protected Game(int gameType) {
        this.GAME_TYPE = gameType;
        this.gameDAO = new GameDAOFactory().create();
        this.MAX_SCORE = maxScore();
        this.random = new Random();
        this.players = initializePlayers();
    }

    private Player[] initializePlayers() {
        int PLAYER_AMOUNT = 4;
        int COUPLE_AMOUNT = 2;
        boolean isSinglePlayer = allowSinglePlayer() && InputHandler.askBoolean("Vols jugar individualment [y/n]?");

        this.teamAmount = isSinglePlayer ? PLAYER_AMOUNT : PLAYER_AMOUNT / COUPLE_AMOUNT;

        int playersPerTeam = PLAYER_AMOUNT / teamAmount; // Clearer logic
        Player[] playerArray = new Player[PLAYER_AMOUNT];

        fillPlayers(playerArray, playersPerTeam);
        return playerArray;
    }

    private void fillPlayers(Player[] playerArray, int playersPerTeam) {
        int current = 0;
        for (int playerIndex = 0; playerIndex < playersPerTeam; playerIndex++) {
            for (int teamIndex = 0; teamIndex < teamAmount; teamIndex++) {
                playerArray[current++] = new Player(teamIndex); // Removed redundant "current++" logic
            }
        }
    }

    private void initPlayerHands() {
        int TOTAL_BONES = 28;
        int bonesPerPlayer = TOTAL_BONES / players.length;
        BoneList boneyard = new BoneList();
        boneyard.fill();
        for (Player player : players) {
            player.setHand(boneyard.takeRandom(bonesPerPlayer));
        }
    }

    public void startGame() {
        gameLoop();
    }

    public void resumeGame() {
        playRound(savedPlayerIndex);
        if (maxScoreReached()) {
            View.displayWinner(establishGameWinner());
            return;
        }
        gameLoop();
    }

    public void gameLoop() {
        int previousWinnerIndex = -1;
        while (true) {
            int openingPlayer;
            initPlayerHands();
            if (firstRound) {
                openingPlayer = getFirstRoundStartingPlayer();
                firstRound = false;
            } else {
                openingPlayer = getNormalRoundStartingPlayer(previousWinnerIndex);
            }
            playRound(openingPlayer);
            if (maxScoreReached()) {
                View.displayWinner(establishGameWinner());
                return;
            }
        }
    }

    public final void playRound(int openingPlayer) {
        int playerIndex = openingPlayer;
        while (true) {
            playerIndex = getNextPlayerOf(playerIndex);
            Player player = players[playerIndex];
            int[] boardEnds = board.getEnds();

            updateScreen(player);
            if (!player.canPlay(boardEnds)) {
                handlePass(playerIndex);
                playerSwap(playerIndex);
                continue;
            }

            int[] playerConstraints = player.getPlayableIndexes(boardEnds);
            player.highlight(playerConstraints);
            updateScreen(player);

            int handIndex = InputHandler.askConstrainedInt(playerConstraints, "Selecciona una peça: ");
            player.unHighlightAll();

            int[] boardConstraints = board.getPlayableIndexes(player.getBone(handIndex));
            board.highlight(boardConstraints);
            updateScreen(player);

            int boardIndex = InputHandler.askConstrainedInt(boardConstraints, "Selecciona una peça: ");
            board.unHighlightAll();

            board.add(player.takeBone(handIndex), boardIndex);
            updateScreen(player);

            if (noOneCanPlay()) {
                handleTranca(player);
                return;
            }

            if (player.getHand().isEmpty()) {
                scoreRoundWinner(player);
                return;
            }

            playerSwap(playerIndex);
        }
    }

    private void updateScreen(Player player) {
        Ansi.clearScreen();
        View.drawBoard(board);
        View.drawPlayerInfo(player);
        View.drawHand(player);
    }

    private boolean noOneCanPlay() {
        for (Player player : players) {
            if (player.canPlay(board.getEnds())) {
                return false;
            }
        }
        return true;
    }

    private void playerSwap(int currentPlayer) {
        InputHandler.waitKeyPress();
        InputHandler.waitPlayerSwap();
        gameDAO.saveAll(this, GAME_TYPE);
        savedPlayerIndex = currentPlayer;
    }

    protected int getNextPlayerOf(int playerIndex) {
        return ++playerIndex % players.length;
    }

    private boolean maxScoreReached() {
        int[] teams = new int[teamAmount];
        for (Player player : players) {
            teams[player.getTeam()] += player.getScore();
            if (teams[player.getTeam()] >= MAX_SCORE) {
                return true;
            }
        }
        return false;
    }

    protected abstract int establishGameWinner();

    protected abstract void handleTranca(Player trancaResponsible);

    protected abstract void handlePass(int currentPlayerIndex);

    protected abstract void scoreRoundWinner(Player winner);

    protected abstract int getFirstRoundStartingPlayer();

    protected abstract int getNormalRoundStartingPlayer(int previousWinnerIndex);

    protected abstract boolean allowSinglePlayer();

    protected abstract int maxScore();

}
