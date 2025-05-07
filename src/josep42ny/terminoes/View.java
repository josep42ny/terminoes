package josep42ny.terminoes;

import java.util.Map;

public class View {

    /**
     * Prints a Bone array to screen arranged in a single row.
     *
     * @param bones An array containing the bones to be printed.
     */
    public void drawHand(BoneList bones) {
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

        String indexes = "";
        for (int i = 0; i < highlights.length; i++) {
            if (highlights[i]) {
                indexes += "     " + i + "     ";
            } else {
                indexes += "           ";
            }
        }
        System.out.println(indexes);
    }

    public void drawHand(Player player) {
        drawHand(player.getHand());
    }

    //todo
    public void drawBoard(Board board) {
        tempHelper(board.getLfArm(), 0);
        tempHelper(board.getRgArm(), 1);
    }

    public void tempHelper(BoneList bones, int index) {
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

        String indexes = "";
        for (boolean highlighted : highlights) {
            if (highlighted) {
                indexes += "     " + index + "     ";
            } else {
                indexes += "           ";
            }
        }
        System.out.println(indexes);
    }

    public void homeScreen() {
        Ansi.clearScreen();
        String out =
                " ____            _             \n" +
                        "|    \\ ___ _____|_|___ ___ ___ \n" +
                        "|  |  | . |     | |   | . |_ -|\n" +
                        "|____/|___|_|_|_|_|_|_|___|___|\n" +
                        "_______________________________\n";

        System.out.println(out);
    }

    public void drawNewGames() {
        String out = " Nova partida\n" +
                " - - - - - - -\n" +
                " [0] Espanyol\n" +
                " [1] Mexicà\n" +
                " [2] Llatinoamericà\n" +
                " [3] Colombià\n" +
                " [4] Chilè\n" +
                " [5] Veneçolà\n" +
                " [6] Ponce\n";

        System.out.println(out);
    }

    public void drawSavedGames(Map<String, Player[]> saves) {
        int index = 0;
        for (Map.Entry<String, Player[]> entry : saves.entrySet()) {
            if (entry.getValue()[0] == null) {
                continue;
            }

            String name = entry.getKey();
            System.out.println(" [" + index++ + "] " + name);
        }
    }

}
