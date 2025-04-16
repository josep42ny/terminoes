package josep42ny.terminoes;
import static josep42ny.terminoes.Color.*;

public class Bone {

    private Ansi ansi;
    private int lf;
    private int rg;
    private Direction direction;
    private boolean highlighted;

    public Bone(int lf, int rg, Direction direction) {
        this.ansi = new Ansi();
        this.lf = lf;
        this.rg = rg;
        this.direction = direction;
        this.highlighted = false;
    }

    public Bone(int lf, int rg) {
        this(lf, rg, Direction.UP);
    }

    public int getLf() {
        return lf;
    }

    public int getRg() {
        return rg;
    }

    public String[] getParts() {
        String[] out;
        switch (direction) {
            case UP,DW -> out = isHighlighted() ? vPartsHiglighted(lf, rg) : vParts(rg, lf);
            case LF,RG -> out = isHighlighted() ? hPartsHiglighted(lf, rg) : hParts(rg, lf);
            default -> out = new String[]{};
        }
        return out;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void highlight() {
        this.highlighted = true;
    }

    public void unHighlight() {
        this.highlighted = false;
    }

    private String[] hParts(int first, int last) {
        return new String[]{
                "         ",
                ansi.p(FG_WHITE).a("▗▄▄▄▄▄▄▄▖").end(),
                ansi.p(FG_WHITE).a("▐").p(FG_BLACK, BG_WHITE).a(" " + first + " │ " + last + " ").p(RESET, FG_WHITE).a("▌").end(),
                ansi.p(FG_WHITE).a("▝▀▀▀▀▀▀▀▘").end(),
                "         "
        };
    }

    private String[] vParts(int first, int last) {
        return new String[]{
                ansi.p(FG_WHITE).a("  ▗▄▄▄▖  ").end(),
                ansi.p(FG_WHITE).a("  ▐").p(FG_BLACK, BG_WHITE).a(" " + first + " ").p(RESET, FG_WHITE).a("▌  ").end(),
                ansi.p(FG_WHITE).a("  ▐").p(FG_BLACK, BG_WHITE).a("───").p(RESET, FG_WHITE).a("▌  ").end(),
                ansi.p(FG_WHITE).a("  ▐").p(FG_BLACK, BG_WHITE).a(" " + last + " ").p(RESET, FG_WHITE).a("▌  ").end(),
                ansi.p(FG_WHITE).a("  ▝▀▀▀▘  ").end()
        };
    }

    private String[] hPartsHiglighted(int first, int last) {
        return new String[]{
                "         ",
                ansi.p(FG_WHITE).a("  ▗▄▄▄▄▄▄▄▖  ").end(),
                ansi.p(FG_WHITE).a("  ▐").p(FG_BLACK, BG_WHITE).a(" " + first + " │ " + last + " ").p(FG_WHITE ,SHADOW).a("▌").end() + "  ",
                ansi.p(FG_WHITE).a("  ▝").p(SHADOW).a("▀▀▀▀▀▀▀▘").end() + "  ",
                "         "
        };
    }

    private String[] vPartsHiglighted(int first, int last) {
        return new String[]{
                ansi.p(FG_WHITE).a("  ▗▄▄▄▖  ").end(),
                ansi.p(FG_WHITE).a("  ▐").p(FG_BLACK, BG_WHITE).a(" " + first + " ").p(FG_WHITE, SHADOW).a("▌").end() + "  ",
                ansi.p(FG_WHITE).a("  ▐").p(FG_BLACK, BG_WHITE).a("───").p(FG_WHITE, SHADOW).a("▌").end() + "  ",
                ansi.p(FG_WHITE).a("  ▐").p(FG_BLACK, BG_WHITE).a(" " + last + " ").p(FG_WHITE, SHADOW).a("▌").end() + "  ",
                ansi.p(FG_WHITE).a("  ▝").p(SHADOW).a("▀▀▀▘").end() + "  "
        };
    }

}
