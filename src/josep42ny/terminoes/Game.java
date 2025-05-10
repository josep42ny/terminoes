package josep42ny.terminoes;

import java.io.Serializable;
import java.util.Random;

public abstract class Game implements Serializable {

    private final GameDAO gameDAO;
    protected Random random;
    protected Player[] players;
    protected BoneList boneyard;
    protected Board board;
    protected int teamAmount;
    protected int playerAmount;
    protected int maxScore;
    protected int current;

    public Game() {
        this.gameDAO = new GameDAOFactory().create();
    }

    protected Game(int teamAmount, int playersInTeam) {
        this.teamAmount = teamAmount;
        this.playerAmount = teamAmount * playersInTeam;
        this.gameDAO = new GameDAOFactory().create();
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

    public Game(int players) {
        this(players, 1);
    }

    public int startGame() {
        return gameLoop();
    }

    public int resumeGame() {
        playRound();
        if (maxScoreReached()) {
            return establishWinner();
        }
        return gameLoop();
    }

    public int gameLoop() {
        while (true) {
            distributeBones();
            current = placeFirstBone();
            advancePlayer();
            playRound();
            if (maxScoreReached()) {
                return establishWinner();
            }
        }
    }

    public final void playRound() {
        while (true) {
            Player player = players[current];
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
                handleTanca();
                return;
            }

            // todo sabado
            if (player.getHand().isEmpty()) {
                for (Player player1 : players) {
                    player.addScore(player1.getHandPoints());
                }
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
        advancePlayer();
        gameDAO.saveAll(this, 0);
    }

    private void advancePlayer() {
        current = ++current % players.length;
    }

    private void distributeBones() {
        int BONES_PER_PLAYER = 28 / players.length;
        for (Player player : players) {
            player.setHand(boneyard.takeRandom(BONES_PER_PLAYER));
        }
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

    protected abstract int establishWinner();

    protected abstract void handleTanca();

    protected abstract void handlePass();

    // returns the index of the player the bone is taken from
    protected abstract int placeFirstBone();

}
