package josep42ny.terminoes;

public class Bone {

    private int lf;
    private int rg;

    public Bone(int lf, int rg) {
        this.lf = lf;
        this.rg = rg;
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

}
