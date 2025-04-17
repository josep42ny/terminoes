package josep42ny.terminoes;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Bone center;
    private BoneList leftArm;
    private BoneList rightArm;

    public Board() {
        this.leftArm = new BoneList();
        this.rightArm = new BoneList();
    }

    public Bone getCenter() {
        return center;
    }

    public void setCenter(Bone center) {
        this.center = center;
    }

    public BoneList getLeftArm() {
        return leftArm;
    }

    public BoneList getRightArm() {
        return rightArm;
    }

    public int[] getEnds() {
        int lf;
        int rg;

        if (leftArm.size() == 0) {
            lf = center.getLf();
        } else {
            lf = leftArm.get(-1).getLf();
        }

        if (rightArm.size() == 0) {
            rg = center.getRg();
        } else {
            rg = rightArm.get(-1).getRg();
        }

        return new int[] {lf, rg};
    }

    public int[] getPlayableIndexes(Bone bone) {
        List<Integer> out = new ArrayList<>();
        int leftValue = bone.getLf();
        int rightValue = bone.getRg();
        int[] ends = getEnds();

        for (int i = 0; i < ends.length; i++) {
            if (leftValue == ends[i] || rightValue == ends[i]) {
                out.add(i);
            }
        }

        return out.stream().mapToInt(i -> i).toArray();
    }

    public void highlight(int[] indexes) {
        for (int index : indexes) {
            switch (index) {
                case 0:
                    if(leftArm.size() > 0) {
                        leftArm.get(-1).highlight();
                    } else {
                        center.highlight();
                    }
                    break;
                case 1:
                    if (rightArm.size() > 0) {
                        rightArm.get(-1).highlight();
                    } else {
                        center.highlight();
                    }
                    break;
            }
        }
    }

    public void unHighlightAll() {
        center.unHighlight();
        leftArm.unHighlightAll();
        rightArm.unHighlightAll();
    }

    public void add(Bone bone, int target) {
        switch (target) {
            case 0 -> leftArm.add(bone);
            case 1 -> rightArm.add(bone);
        }
    }

}
