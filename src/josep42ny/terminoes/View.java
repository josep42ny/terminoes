package josep42ny.terminoes;

public class View {

    /**
     * Prints a Bone array to screen arranged in a single row.
     *
     * @param bones An array containing the bones to be printed.
     */
    public void drawHand(BoneList bones) {
        if (bones.size() == 0) return;
        boolean[] highlights = new boolean[bones.size()];

        for (int i = 0; i < bones.get(0).getParts().length; i++) {
            for (int j = 0; j < bones.size(); j++) {
                System.out.print(bones.get(j).getParts()[i] + " ");
                if (bones.get(j).isHighlighted()) {
                    highlights[j] = true;
                }
            }
            System.out.println();
        }

        String indexes = "";
        for (int i = 0; i < highlights.length; i++) {
            if (highlights[i]) {
                indexes += "    " + i + "     ";
            } else {
                indexes += "          ";
            }
        }
        System.out.println(indexes);
    }

    public void drawHand(Player player) {
        drawHand(player.getHand());
    }

    //todo
    public void drawBoard(Board board) {
        drawHand(new BoneList(board.getCenter()));
        if (board.getLeftArm().size() > 0) drawHand(board.getLeftArm()) ;
        if (board.getRightArm().size() > 0) drawHand(board.getRightArm()) ;
    }

}
