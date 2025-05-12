package josep42ny.terminoes.utilities;

import josep42ny.terminoes.Board;
import josep42ny.terminoes.BoneList;
import josep42ny.terminoes.Game;
import josep42ny.terminoes.Player;
import josep42ny.terminoes.Bone;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class View {

    private static final String empty = "           ";

    public static void drawHand(BoneList bones) {
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

    public static void drawHand(Player player) {
        drawHand(player.getHand());
    }

    public static void drawBoard(Board board) {
        List<Bone[]> partsLf = new ArrayList<>(6);

        BoneList lf = board.getLfArm();
        int height = (lf.size() + 2) / 7;
        int s = 3;
        int index = 0;

        for (int i = 0; i <= height; i++) {

            Bone[] part = new Bone[7];

            for (int j = s; j < part.length; j++) {
                if (index >= lf.size()) {
                    part[j] = null;
                } else {
                    part[j] = lf.get(index++);
                }
                s = 0;
            }

            partsLf.add(part);
        }

        for (Bone[] part : partsLf.reversed()) {
            tempHelper(new BoneList(part), 1);
        }

        List<Bone[]> partsRg = new ArrayList<>(6);
        BoneList rg = board.getRgArm();
        int heightrg = (rg.size() + 2) / 7;
        int d = 3;
        int indexrg = 0;

        for (int i = 0; i <= heightrg; i++) {

            Bone[] part = new Bone[7];

            for (int j = d; j < part.length; j++) {
                if (indexrg >= rg.size()) {
                    part[j] = null;
                } else {
                    part[j] = rg.get(indexrg++);
                }
                d = 0;
            }

            partsRg.add(part);
        }

        for (Bone[] part : partsRg.reversed()) {
            tempHelper(new BoneList(part), 1);
        }

//        BoneList lf = board.getLfArm();
//        int height = (lf.size() + 2) / 7;
//        System.out.println(height);
//
//        Bone[][] outer = new Bone[height + 1][7];
//        for (int i = height; i >= 0; i--) {
//            System.out.println(7 % ((i * 7) + 3));
//            //for (int j = 0; j < ; j++) {
//            //outer[i][j] =
//            //}
//        }

        //tempHelper(board.getLfArm(), 0);
        //tempHelper(board.getRgArm(), 1);
    }

    public static void tempHelper(BoneList bones, int index) {
        if (bones.isEmpty()) return;
        boolean[] highlights = new boolean[bones.size()];
        int PART_AMOUNT = 5;
        for (int i = 0; i < PART_AMOUNT; i++) {
            for (int j = 0; j < bones.size(); j++) {
                if (bones.get(j) == null) {
                    System.out.print("...........");
                } else {
                    System.out.print(bones.get(j).getParts()[i]);
                    if (bones.get(j).isHighlighted()) {
                        highlights[j] = true;
                    }
                }
            }
            System.out.println();
        }

        StringBuilder indexes = new StringBuilder();
        for (boolean highlighted : highlights) {
            if (highlighted) {
                indexes.append("     ").append(index).append("     ");
            } else {
                indexes.append("           ");
            }
        }
        System.out.println(indexes);
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
