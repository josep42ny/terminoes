package josep42ny.terminoes;

import java.util.Arrays;

public class Bone {

    private int lf;
    private int rg;
    private Direction direction;

    public Bone(int lf, int rg, Direction direction) {
        this.lf = lf;
        this.rg = rg;
        this.direction = direction;
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
        String out;
        switch (direction) {
            case UP -> out = vParts(lf, rg)[index];
            case RG -> out = hParts(lf, rg)[index];
            case DW -> out = vParts(rg, lf)[index];
            case LF -> out = hParts(rg, lf)[index];
            default -> out = "";
        }
        return out;
    }

    private String[] hParts(int first, int last) {
        return new String[]{
                "\033[97;1m" + "▗▄▄▄▄▄▄▄▖" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + first + " │ " + last + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀▀▀▀▀▘" + "\033[0m"
        };
    }

    private String[] vParts(int first, int last) {
        return new String[]{
                "\033[97;1m" + "▗▄▄▄▖" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + first + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + "───" + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + last + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀▘" + "\033[0m"
        };
    }

}
