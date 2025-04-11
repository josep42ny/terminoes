package josep42ny.terminoes;

public class Player {

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

    public void setHand(BoneList hand) {
        this.hand = hand;
    }
}
