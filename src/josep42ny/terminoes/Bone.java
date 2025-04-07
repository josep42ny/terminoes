package josep42ny.terminoes;

import java.util.Arrays;

public class Bone {

    private int lf;
    private int rg;
    private int[] direction;
    private final String[] visualHorizontal;
    private final String[] visualVertical;

    public Bone(int lf, int rg, int[] direction) {
        this.lf = lf;
        this.rg = rg;
        this.direction = direction;
        visualHorizontal = new String[]{
                "\033[97;1m" + "▗▄▄▄▄▄▄▄▖" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + this.lf + " │ " + this.rg + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀▀▀▀▀▘" + "\033[0m"
        };
        visualVertical = new String[]{
                "\033[97;1m" + "▗▄▄▄▖" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + this.lf + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + "───" + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + this.rg + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀▘" + "\033[0m"
        };
    }

    public int getLeftValue() {
        return lf;
    }

    public int getRightValue() {
        return rg;
    }

    public int getValue(int index) throws IndexOutOfBoundsException {
        if (index == 0) {
            return lf;
        } else if (index == 1) {
            return rg;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public String getPart(int index) {
        if (Arrays.equals(direction, new int[]{1, 0})) {
            return visualHorizontal[index];
        } else {
            return visualVertical[index];
        }
    }

    public int getVisualSize() {
        if (Arrays.equals(direction, new int[]{1, 0})) {
            return visualHorizontal.length;
        } else {
            return visualVertical.length;
        }
    }

}
