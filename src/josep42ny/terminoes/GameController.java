package josep42ny.terminoes;

public class GameController {

    View view;

    public GameController() {
        this.view = new View();
    }

    public void awake() {
        BoneList boneyard = new BoneList();
        view.drawHand(boneyard);
    }

}
