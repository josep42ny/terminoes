package josep42ny.terminoes;

public class View {

    /**
     * Prints a BoneList to screen arranged in a single row.
     *
     * @param boneList A list containing the bones to be printed.
     */
    public void drawHand(BoneList boneList) {
        int listSize = boneList.size();

        for (int a = 0; a < listSize; a++) {
            System.out.print("\033[;97;1m" + "▗" + "\033[0m" + "\033[97;1m" + "▄▄▄" + "\033[0m" + "\033[;97;1m" + "▖" + "\033[0m" + " ");
        }
        System.out.println();
        for (int i = 0; i < listSize; i++) {
            System.out.print("\033[97;1m" + "▐" + "\033[0m" + "\033[107;30;1m" + " " + boneList.get(i).getValue(0) + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m" + " ");
        }
        System.out.println();
        for (int a = 0; a < listSize; a++) {
            System.out.print("\033[97;1m" + "▐" + "\033[0m" + "\033[107;30;1m" + "───" + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m" + " ");
        }
        System.out.println();
        for (int i = 0; i < listSize; i++) {
            System.out.print("\033[97;1m" + "▐" + "\033[0m" + "\033[107;30;1m" + " " + boneList.get(i).getValue(1) + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m" + " ");
        }
        System.out.println();
        for (int a = 0; a < listSize; a++) {
            System.out.print("\033[;97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀" + "\033[0m" + "\033[105;97;1m" + "▘" + "\033[0m" + " ");
        }
        System.out.println();
    }

}
