package josep42ny.terminoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BoneList implements Serializable {

    private final Random random = new Random();
    private final List<Bone> bones = new ArrayList<>();

    public BoneList() {
    }

    public BoneList(Bone... bones) {
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

    public Bone take(int index) {
        return bones.remove(index);
    }

    public BoneList takeRandom(int amount) {
        BoneList out = new BoneList();

        for (int i = 0; i < amount; i++) {
            if (bones.isEmpty()) break;
            out.addLast(this.take(random.nextInt(this.size())));
        }

        return out;
    }

    public int size() {
        return bones.size();
    }

    public Bone get(int index) {
        return bones.get(index >= 0 ? index : bones.size() + index);
    }

    public int getLastBoneValue() {
        if (bones.isEmpty()) return -1;
        Bone lastBone = bones.getLast();
        return lastBone.getDirection() == Direction.RG ? lastBone.getLf() : lastBone.getRg();
    }

    public int getFirstBoneValue() {
        if (bones.isEmpty()) return -1;
        Bone firstBone = bones.getFirst();
        return firstBone.getDirection() == Direction.LF ? firstBone.getLf() : firstBone.getRg();
    }

    public int[] getEndNumbers() {
        return new int[]{getFirstBoneValue(), getLastBoneValue()};
    }

    public void addLast(Bone bone) {
        bones.add(bone);
    }

    public void placeLast(Bone bone) {
        int lf = bone.getLf();
        int rg = bone.getRg();
        int end = getLastBoneValue();

        if (lf == rg && rg == end) {
            bone.setDirection(Direction.UP);
        } else if (lf == end) {
            bone.setDirection(Direction.LF);
        } else if (rg == end) {
            bone.setDirection(Direction.RG);
        }

        bones.addLast(bone);
    }

    public void placeFirst(Bone bone) {
        int lf = bone.getLf();
        int rg = bone.getRg();
        int end = getFirstBoneValue();

        if (lf == rg && rg == end) {
            bone.setDirection(Direction.UP);
        } else if (lf == end) {
            bone.setDirection(Direction.RG);
        } else if (rg == end) {
            bone.setDirection(Direction.LF);
        }

        bones.addFirst(bone);
    }

    public int[] getPlayableIndexes(int[] targets) {
        List<Integer> out = new ArrayList<>();

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
        for (int i = 0; i < bones.size(); i++) {
            Bone bone = bones.get(i);
            if (bone.getLf() == lfValue && bone.getRg() == rgValue) {
                return bones.remove(i);
            }
        }
        return null; // return null if bone not found
    }

    public void highlight(int... indexes) {
        for (int index : indexes) {
            bones.get(index).highlight();
        }
    }

    public void unHighlightAll() {
        for (Bone bone : bones) {
            bone.unHighlight();
        }
    }

    public boolean isEmpty() {
        return bones.isEmpty();
    }

    public boolean canPlay(int[] targets) {
        for (int target : targets) {
            for (Bone bone : bones) {
                if (bone.getLf() == target || bone.getRg() == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getPoints() {
        int points = 0;
        for (Bone bone : bones) {
            points += bone.getLf() + bone.getRg();
        }
        return points;
    }

    public Bone takeBiggest() {
        int biggest = 0;
        for (int i = 0; i < bones.size(); i++) {
            if (bones.get(i).getValue() > bones.get(biggest).getValue()) {
                biggest = i;
            }
        }
        return take(biggest);
    }

}
