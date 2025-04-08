package josep42ny.terminoes;

public class GameMexican extends Game {

    View view;
    BoneList boneyard;
    BoneList[][] teams;
    BoneList leftEnd;
    BoneList rightEnd;

    public GameMexican(int players, int teams) {
        final int PIECE_NUMBER = 28;
        this.view = new View();
        this.boneyard = new BoneList();
        this.boneyard.fill();
        this.leftEnd = new BoneList();
        this.rightEnd = new BoneList();
        this.teams = new BoneList[teams][players];

        for (int i = 0; i < teams; i++) {
            for (int j = 0; j < players; j++) {
                this.teams[i][j] = new BoneList(this.boneyard.takeRandomBones(PIECE_NUMBER / teams / players));
            }
        }
    }

    public GameMexican(int players) {
        this(1, players);
    }

    @Override
    public void playRound() {
        BoneList test = teams[0][0];
        view.drawHand(test.getAllBones());

        leftEnd.add(new Bone(6, 6, Direction.UP));
        view.drawHand(leftEnd.getAllBones());

        for (int i = 0; i < test.size(); i++) {
            if (leftEnd.get(0).getLeftValue() == test.get(i).getLeftValue()) {
                leftEnd.add(test.take(i));
                break;
            }
        }

        view.drawHand(test.getAllBones());

        view.drawHand(leftEnd.getAllBones());

    }

}
