package josep42ny.terminoes;

public enum Color {

    RESET("\033[m"),
    RED("\033[101m"),
    BG_WHITE("\033[107m"),
    FG_WHITE("\033[97m"),
    BG_BLACK("\033[40m"),
    FG_BLACK("\033[30m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }

}

