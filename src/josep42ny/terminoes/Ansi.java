package josep42ny.terminoes;

public class Ansi {

    private static String string = "";

    public Ansi draw() {
        return this;
    }

    public Ansi p(Color color) {
        string += color;
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
}
