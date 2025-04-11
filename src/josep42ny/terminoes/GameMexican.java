package josep42ny.terminoes;

public class GameMexican extends Game {

    View view;
    BoneList boneyard;
    BoneList[][] teams;
    BoneList leftEnd;
    BoneList rightEnd;

    public GameMexican(int players, int teams) {
        super(teams, players);
//        final int PIECE_NUMBER = 28;
//        this.view = new View();
//        this.boneyard = new BoneList();
//        this.boneyard.fill();
//        this.leftEnd = new BoneList();
//        this.rightEnd = new BoneList();
//        this.teams = new BoneList[teams][players];
//
//        for (int i = 0; i < teams; i++) {
//            for (int j = 0; j < players; j++) {
//                this.teams[i][j] = new BoneList(this.boneyard.takeRandom(PIECE_NUMBER / teams / players));
//            }
//        }
    }

    public GameMexican(int players) {
        this(1, players);
    }

    //Prep
    //Bone centerBone = chooseCenterBone();

    //loop:teams
    //loop:players in team
    //if player hand can place bone
    //append bone to corresponding side
    //else
    //try: get bone from pile
    //catch: continue to next player
    //end loop
    //end loop


//    private boolean roundEnded() {
//
//    }

}
