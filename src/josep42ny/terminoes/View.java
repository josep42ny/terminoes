package josep42ny.terminoes;

public class View {

    /**
     * Prints a BoneList to screen arranged in a single row.
     *
     * @param boneList A list containing the bones to be printed.
     */
    public void drawHand(BoneList boneList) {
        for (int i = 0; i < boneList.get(0).getParts().length; i++) {
            for (Bone bone : boneList.getAllBones()) {
                System.out.print(bone.getParts()[i] + " ");
            }
            System.out.println();
        }
    }

}
