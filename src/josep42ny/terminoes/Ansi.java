package josep42ny.terminoes;

public class Ansi {

    private static String string = "";

    public Ansi(){}

    public Ansi p(Color ...colors) {
        for (Color color : colors) {
            string += color;
        }
        return this;
    }

    public Ansi a(String str) {
        string += str;
        return this;
    }

    public String end() {
        String out = string + Color.RESET;
        string = "";
        return out;
    }

    public static void clearPreviousLine() {
        System.out.print("\033[F");
        System.out.print("\033[2K");
        System.out.flush();
    }

}
