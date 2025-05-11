package josep42ny.terminoes;

public class Main {

    public static void main(String[] args) {
        Thread printingHook = new Thread(() -> System.out.println("\nSortint...\n"));
        Runtime.getRuntime().addShutdownHook(printingHook);

        GameController gameController = new GameController();
        gameController.awake();
    }

}
