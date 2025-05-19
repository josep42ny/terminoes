package josep42ny.terminoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {

    private final BoneList bones;

    public Board(Bone center) {
        this.bones = new BoneList(center);
    }

    public BoneList getBones() {
        return bones;
    }

    public int[] getEnds() {
        return bones.getEndNumbers();
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
                case 0 -> bones.highlight(0);
                case 1 -> bones.highlight(bones.size() - 1);
            }
        }
    }

    public void unHighlightAll() {
        bones.unHighlightAll();
    }

    public void add(Bone bone, int target) {
        switch (target) {
            case 0 -> bones.placeFirst(bone);
            case 1 -> bones.placeLast(bone);
        }

    }

}
