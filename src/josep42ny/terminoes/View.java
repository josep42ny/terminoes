package josep42ny.terminoes;

public class View {

    /**
     * Prints a BoneList to screen arranged in a single row.
     *
     * @param boneList A list containing the bones to be printed.
     */
    public void drawHand(BoneList boneList) {
        for (int i = 0; i < boneList.get(i).getVisualSize(); i++) {
            for (int j = 0; j < boneList.size(); j++) {
                System.out.print(boneList.get(j).getPart(i) + " ");
            }
            System.out.println();
        }
    }

}
