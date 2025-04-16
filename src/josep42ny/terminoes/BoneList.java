package josep42ny.terminoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BoneList {

    private final Random random = new Random();
    private final List<Bone> bones = new ArrayList<>();

    public BoneList() {
    }

    public BoneList(Bone[] bones) {
        this.bones.addAll(Arrays.asList(bones));
    }

    public void fill() {
        final int MAX_BONE_VALUE = 6;
        for (int lf = 0; lf <= MAX_BONE_VALUE; lf++) {
            for (int rg = 0; rg <= lf; rg++) {
                bones.add(new Bone(lf, rg));
            }
        }
    }

    public Bone[] toArray() {
        return bones.toArray(new Bone[0]);
    }

    public Bone take(int index) {
        return bones.remove(index);
    }

    public BoneList takeRandom(int amount) throws OutOfBones {
        if (bones.isEmpty()) {
            throw new OutOfBones("tried getting bones from an empty list");
        }

        BoneList out = new BoneList();

        for (int i = 0; i < amount; i++) {
            out.add(this.take(random.nextInt(this.size())));
        }

        return out;
    }

    public int size() {
        return bones.size();
    }

    public Bone get(int index) {
        return index >= 0 ? bones.get(index) : bones.get(bones.size() + index);
    }

    public void add(Bone bone) {
        bones.add(bone);
    }

    public int[] getPlayableIndexes(int[] targets) {
        List<Integer> out = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < bones.size(); i++) {
            for (int target : targets) {
                if (bones.get(i).getLf() == target || bones.get(i).getRg() == target) {
                    out.add(i);
                }
            }
        }

        return out.stream().mapToInt(i -> i).toArray();
    }

    public boolean hasBone(int lfValue, int rgValue) {
        for (Bone bone : bones) {
            if (bone.getLf() == lfValue && bone.getRg() == rgValue) {
                return true;
            }
        }
        return false;
    }

    public Bone takeBoneByValue(int lfValue, int rgValue) {
        int match = 0;
        for (int i = 0; i < bones.size(); i++) {
            Bone bone = bones.get(i);
            if (bone.getLf() == lfValue && bone.getRg() == rgValue) {
                match = i;
            }
        }
        return bones.remove(match);
    }

    public void highlight(int[] indexes) {
        for (int index : indexes) {
            bones.get(index).highlight();
        }
    }

}
