package josep42ny.terminoes;

public class Bone {

    private int lf;
    private int rg;
    private final String[] visualParts;

    public Bone(int lf, int rg) {
        this.lf = lf;
        this.rg = rg;
        this.visualParts = new String[3];
        //this.visualParts[0] = "\033[97;1m" + "▗▄▄▄▖" + "\033[0m";
        //this.visualParts[1] = "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + this.lf + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m";
        //this.visualParts[2] = "\033[97;1m" + "▐" + "\033[107;30;1m" + "───" + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m";
        //this.visualParts[3] = "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + this.rg + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m";
        //this.visualParts[4] = "\033[97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀▘" + "\033[0m";
        this.visualParts[0] = "\033[97;1m" + "▗▄▄▄▄▄▄▄▖" + "\033[0m";
        this.visualParts[1] = "\033[97;1m" + "▐" + "\033[107;30;1m" + " " + this.lf + " │ " + this.rg + " " + "\033[0m" + "\033[105;97;1m" + "▌" + "\033[0m";
        this.visualParts[2] = "\033[97;1m" + "▝" + "\033[0m" + "\033[97;105;1m" + "▀▀▀▀▀▀▀▘" + "\033[0m";
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
        return visualParts[index];
    }

    public int getVisualHeight() {
        return visualParts.length;
    }

}
