package josep42ny.terminoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    private Bone center;
    private final BoneList lfArm;
    private final BoneList rgArm;

    public Board() {
        this.lfArm = new BoneList();
        this.rgArm = new BoneList();
    }

    public void setCenter(Bone center) {
        this.center = center;
        this.lfArm.add(center);
        this.rgArm.add(center);
    }

    public BoneList getLfArm() {
        return lfArm;
    }

    public BoneList getRgArm() {
        return rgArm;
    }

    public int[] getEnds() {
        int lfEnd = lfArm.getEnd();
        int rgEnd = rgArm.getEnd();

        return new int[]{lfEnd, rgEnd};
    }

    public int[] getPlayableIndexes(Bone bone) {
        List<Integer> out = new ArrayList<>();
        int leftValue = bone.getLf();
        int rightValue = bone.getRg();
        int[] ends = this.getEnds();

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
                case 0 -> lfArm.get(-1).highlight();
                case 1 -> rgArm.get(-1).highlight();
            }
        }
    }

    public void unHighlightAll() {
        center.unHighlight();
        lfArm.unHighlightAll();
        rgArm.unHighlightAll();
    }

    public void add(Bone bone, int target) {
        switch (target) {
            case 0 -> lfArm.addOriented(bone);
            case 1 -> rgArm.addOriented(bone);
        }

    }

}
