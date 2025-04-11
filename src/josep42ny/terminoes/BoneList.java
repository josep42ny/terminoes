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
        int MAX_BONE_VALUE = 6;
        for (int lf = 0; lf <= MAX_BONE_VALUE; lf++) {
            for (int rg = 0; rg <= lf; rg++) {
                bones.add(new Bone(lf, rg, Direction.RG));
            }
        }
    }

    public Bone[] getAllBones() {
        return bones.toArray(new Bone[0]);
    }

    public Bone take(int index) {
        return bones.remove(index);
    }

    public Bone[] takeRandomBones(int amount) {
        Bone[] out = new Bone[amount];

        for (int i = 0; i < amount; i++) {
            out[i] = bones.remove(random.nextInt(bones.size()));
        }

        return out;
    }

    public int size() {
        return bones.size();
    }

    public Bone get(int index) {
        return bones.get(index);
    }

    public void add(Bone bone) {
        bones.add(bone);
    }

    public int[] getPlayableIndexes(int target) {
        List<Integer> out = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < bones.size(); i++) {
            if (bones.get(i).getLeftValue() == target || bones.get(i).getRightValue() == target) {
                out.add(i);
            }
        }

        return out.stream().mapToInt(i -> i).toArray();
    }

}
