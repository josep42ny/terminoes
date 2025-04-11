package josep42ny.terminoes;

public class View {

    /**
     * Prints a Bone array to screen arranged in a single row.
     *
     * @param bones An array containing the bones to be printed.
     */
    public void drawHand(BoneList bones) {
        for (int i = 0; i < bones.get(0).getParts().length; i++) {
            for (Bone bone : bones.toArray()) {
                System.out.print(bone.getParts()[i] + " ");
            }
            System.out.println();
        }
    }

}
