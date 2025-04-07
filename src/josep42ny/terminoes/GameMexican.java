package josep42ny.terminoes;

public class GameMexican extends Game {

    View view;
    BoneList boneyard;

    public GameMexican() {
        this.view = new View();
        this.boneyard = new BoneList();
    }

    @Override
    public void playRound() {
        for (int i = 0; i < 4; i++) {
            view.drawHand(boneyard.takeRandomBones(7));
        }
    }

}
