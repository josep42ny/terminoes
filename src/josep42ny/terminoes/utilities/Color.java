package josep42ny.terminoes.utilities;

public enum Color {

    RESET("\033[m"),
    BG_WHITE("\033[107m"),
    FG_WHITE("\033[97m"),
    FG_BLACK("\033[30m"),
    SHADOW("\033[102m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}

