package josep42ny.terminoes;

public class Board {

    private Bone center;
    private BoneList leftEnd;
    private BoneList rightEnd;

    public Board() {
        this.leftEnd = new BoneList();
        this.rightEnd = new BoneList();
    }

    public Bone getCenter() {
        return center;
    }

    public void setCenter(Bone center) {
        this.center = center;
    }

    public BoneList getLeftEnd() {
        return leftEnd;
    }

    public void setLeftEnd(BoneList leftEnd) {
        this.leftEnd = leftEnd;
    }

    public BoneList getRightEnd() {
        return rightEnd;
    }

    public void setRightEnd(BoneList rightEnd) {
        this.rightEnd = rightEnd;
    }
//fixme
    public int[] getEnds() {
        int lf;
        int rg;

        if (leftEnd.size() == 0) {
            lf = center.getLf();
        } else {
            lf = leftEnd.get(-1).getLf();
        }

        if (rightEnd.size() == 0) {
            rg = center.getRg();
        } else {
            rg = rightEnd.get(-1).getRg();
        }

        return new int[] {lf, rg};
    }
}
