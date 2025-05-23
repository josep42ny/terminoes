package josep42ny.terminoes.utilities;

import josep42ny.terminoes.*;

import java.util.Map;

public class View {

    public static void drawHand(Player player) {
        BoneList bones = player.getHand();
        if (bones.isEmpty()) return;
        boolean[] highlights = new boolean[bones.size()];

        for (int i = 0; i < bones.get(0).getParts().length; i++) {
            for (int j = 0; j < bones.size(); j++) {
                System.out.print(bones.get(j).getParts()[i]);
                if (bones.get(j).isHighlighted()) {
                    highlights[j] = true;
                }
            }
            System.out.println();
        }

        StringBuilder indexes = new StringBuilder();
        for (int i = 0; i < highlights.length; i++) {
            if (highlights[i]) {
                indexes.append("     ").append(i).append("     ");
            } else {
                indexes.append("           ");
            }
        }
        System.out.println(indexes);
    }

    public static void drawBoard(Board board) {
        BoneList bones = board.getBones();
        if (bones.isEmpty()) return;

        for (int i = 0; i < bones.get(0).getParts().length; i++) {
            for (int j = 0; j < bones.size(); j++) {
                System.out.print(bones.get(j).getParts()[i]);
            }
            System.out.println();
        }

        StringBuilder indexes = new StringBuilder();
        for (int i = 0; i < bones.size(); i++) {
            if (i == 0 && bones.get(i).isHighlighted()) {
                indexes.append("     ").append(0).append("     ");
            } else if (i == bones.size() - 1 && bones.get(i).isHighlighted()) {
                indexes.append("     ").append(1).append("     ");
            } else {
                indexes.append("           ");
            }
        }
        System.out.println(indexes);
    }

    public static void drawPlayerInfo(Player player) {
        String score = String.format("%03d", player.getScore()) + " punts";
        String team = "Equip " + player.getTeam();
        System.out.println("============================ " + team + " - " + score + " ============================\n");
    }

    public static void homeScreen() {
        Ansi.clearScreen();
        String out = """
                 ____            _            \s
                |    \\ ___ _____|_|___ ___ ___\s
                |  |  | . |     | |   | . |_ -|
                |____/|___|_|_|_|_|_|_|___|___|
                _______________________________
                """;

        System.out.println(out);
    }

    public static void drawNewGames() {
        String out = """
                 Nova partida
                 - - - - - - -
                  [0] Espanyol
                  [1] Mexicà
                  [2] Llatinoamericà
                  [3] Colombià
                  [4] Chilè
                  [5] Veneçolà
                  [6] Ponce
                """;

        System.out.println(out);
    }

    public static void drawSavedGames(Map<String, Game> saves) {
        int index = 0;
        for (Map.Entry<String, Game> entry : saves.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }

            String name = entry.getKey();
            System.out.println(" [" + index++ + "] " + name);
        }
    }

    public static void displayWinner(int winnerId) {
        Ansi.clearScreen();

        System.out.println("L'equip o jugador guanyador es el " + winnerId);
        System.out.println("Felicitats");
    }

}
