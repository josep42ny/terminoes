package josep42ny.terminoes;

public class Bone {

    private int lf;
    private int rg;
    private Direction direction;

    public Bone(int lf, int rg, Direction direction) {
        this.lf = lf;
        this.rg = rg;
        this.direction = direction;
    }

    public Bone(int lf, int rg) {
        this(lf, rg, Direction.UP);
    }

    public int getLf() {
        return lf;
    }

    public int getRg() {
        return rg;
    }

    public String[] getParts() {
        String[] out;
        switch (direction) {
            case UP -> out = vParts(lf, rg);
            case RG -> out = hParts(lf, rg);
            case DW -> out = vParts(rg, lf);
            case LF -> out = hParts(rg, lf);
            default -> out = new String[]{};
        }
        return out;
    }

    private String[] hParts(int first, int last) {
        return new String[]{
                "         ",
                "\033[97;1m" + "▗▄▄▄▄▄▄▄▖" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[30;107;1m" + " " + first + " │ " + last + " " + "\033[0m" + "\033[97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▝" + "▀▀▀▀▀▀▀▘" + "\033[0m",
                "         "
        };
    }

    private String[] vParts(int first, int last) {
        return new String[]{
                "  " + "\033[97;1m" + "▗▄▄▄▖" + "\033[0m" + "  ",
                "  " + "\033[97;1m" + "▐" + "\033[107;1m" + " " + "\033[1;30m" + first + " " + "\033[0m" + "\033[97;1m" + "▌" + "\033[0m" + "  ",
                "  " + "\033[97;1m" + "▐" + "\033[107;30;1m" + "───" + "\033[0m" + "\033[97;1m" + "▌" + "\033[0m" + "  ",
                "  " + "\033[97;1m" + "▐" + "\033[107;1m" + " " + "\033[1;30m" + last + " " + "\033[0m" + "\033[97;1m" + "▌" + "\033[0m" + "  ",
                "  " + "\033[97;1m" + "▝" + "▀▀▀▘" + "\033[0m" + "  "
        };
    }

    private String[] hPartsHiglighted(int first, int last) {
        return new String[]{
                "         ",
                "\033[97;1m" + "▗▄▄▄▄▄▄▄▖" + "\033[0m",
                "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + first + " │ " + last + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m",
                "\033[97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀▀▀▀▀▘" + "\033[0m",
                "         "
        };
    }

    private String[] vPartsHiglighted(int first, int last) {
        return new String[]{
                "  " + "\033[97;1m" + "▗▄▄▄▖" + "\033[0m" + "  ",
                "  " + "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + first + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m" + "  ",
                "  " + "\033[97;1m" + "▐" + "\033[107;30;1m" + "───" + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m" + "  ",
                "  " + "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + last + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m" + "  ",
                "  " + "\033[97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀▘" + "\033[0m" + "  "
        };
    }

}
