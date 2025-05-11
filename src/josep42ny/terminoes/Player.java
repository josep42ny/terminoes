package josep42ny.terminoes;

import java.io.Serializable;

public class Player implements Serializable {

    private int team;
    private BoneList hand;
    private int score;

    public Player(int team) {
        this.team = team;
        this.score = 0;
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

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void subScore(int score) {
        this.score = Math.max(0, this.score - score);
    }

    public boolean canPlay(int... targets) {
        return hand.canPlay(targets);
    }

    public boolean hasBone(int lfValue, int rgValue) {
        return hand.hasBone(lfValue, rgValue);
    }

    public Bone takeBoneByValue(int lfValue, int rgValue) {
        return hand.takeBoneByValue(lfValue, rgValue);
    }

    public BoneList takeRandom(int amount) throws OutOfBones {
        return hand.takeRandom(amount);
    }

    public int getHandPoints() {
        return hand.getPoints();
    }

    public Bone takeBiggest() {
        return hand.takeBiggest();
    }

}
