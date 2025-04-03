package josep42ny.terminoes;

public class GameController {

    View view;

    public GameController() {
        this.view = new View();
    }

    public void awake() {
        BoneList boneyard = new BoneList();
        for (int i = 0; i < 4; i++) {
            view.drawHand(boneyard.takeRandomBones(7));
        }
    }

}
