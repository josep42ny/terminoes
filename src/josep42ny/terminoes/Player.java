package josep42ny.terminoes;

import java.io.Serializable;

public class Player implements Serializable {

    private int team;
    private BoneList hand;

    public Player(int team) {
        this.team = team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getTeam() {
        return team;
    }

    public BoneList getHand() {
        return hand;
    }

    public int[] getPlayableIndexes(int[] targets) {
        return hand.getPlayableIndexes(targets);
    }

    public void highlight(int[] targets) {
        hand.highlight(targets);
    }

    public void unHighlightAll() {
        hand.unHighlightAll();
    }

    public Bone getBone(int index) {
        return hand.get(index);
    }

    public Bone takeBone(int index) {
        return hand.take(index);
    }

    public void setHand(BoneList hand) {
        this.hand = hand;
    }
}
