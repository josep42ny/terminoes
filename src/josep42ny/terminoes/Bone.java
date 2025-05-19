package josep42ny.terminoes;

import josep42ny.terminoes.utilities.Ansi;

import java.io.Serializable;

import static josep42ny.terminoes.utilities.Color.*;

public class Bone implements Serializable {

    private final Ansi ansi;
    private final int lf;
    private final int rg;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getValue() {
        return lf + rg;
    }

    public String[] getParts() {
        String[] out;
        switch (direction) {
            case UP -> out = isHighlighted() ? vPartsHighlighted(lf, rg) : vParts(lf, rg);
            case DW -> out = isHighlighted() ? vPartsHighlighted(rg, lf) : vParts(rg, lf);
            case RG -> out = isHighlighted() ? hPartsHighlighted(rg, lf) : hParts(rg, lf);
            case LF -> out = isHighlighted() ? hPartsHighlighted(lf, rg) : hParts(lf, rg);
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
                "           ",
                ansi.p(FG_WHITE).a(" ▗▄▄▄▄▄▄▄▖ ").end(),
                ansi.p(FG_WHITE).a(" ▐").p(FG_BLACK, BG_WHITE).a(" " + first + " │ " + last + " ").p(RESET, FG_WHITE).a("▌ ").end(),
                ansi.p(FG_WHITE).a(" ▝▀▀▀▀▀▀▀▘ ").end(),
                "           "
        };
    }

    private String[] vParts(int first, int last) {
        return new String[]{
                ansi.p(FG_WHITE).a("   ▗▄▄▄▖   ").end(),
                ansi.p(FG_WHITE).a("   ▐").p(FG_BLACK, BG_WHITE).a(" " + first + " ").p(RESET, FG_WHITE).a("▌   ").end(),
                ansi.p(FG_WHITE).a("   ▐").p(FG_BLACK, BG_WHITE).a("───").p(RESET, FG_WHITE).a("▌   ").end(),
                ansi.p(FG_WHITE).a("   ▐").p(FG_BLACK, BG_WHITE).a(" " + last + " ").p(RESET, FG_WHITE).a("▌   ").end(),
                ansi.p(FG_WHITE).a("   ▝▀▀▀▘   ").end()
        };
    }

    private String[] hPartsHighlighted(int first, int last) {
        return new String[]{
                "           ",
                ansi.p(FG_WHITE).a(" ▗▄▄▄▄▄▄▄▖ ").end(),
                ansi.p(FG_WHITE).a(" ▐").p(FG_BLACK, BG_WHITE).a(" " + first + " │ " + last + " ").p(FG_WHITE, SHADOW).a("▌").end() + " ",
                ansi.p(FG_WHITE).a(" ▝").p(SHADOW).a("▀▀▀▀▀▀▀▘").end() + " ",
                "           "
        };
    }

    private String[] vPartsHighlighted(int first, int last) {
        return new String[]{
                ansi.p(FG_WHITE).a("   ▗▄▄▄▖   ").end(),
                ansi.p(FG_WHITE).a("   ▐").p(FG_BLACK, BG_WHITE).a(" " + first + " ").p(FG_WHITE, SHADOW).a("▌").end() + "   ",
                ansi.p(FG_WHITE).a("   ▐").p(FG_BLACK, BG_WHITE).a("───").p(FG_WHITE, SHADOW).a("▌").end() + "   ",
                ansi.p(FG_WHITE).a("   ▐").p(FG_BLACK, BG_WHITE).a(" " + last + " ").p(FG_WHITE, SHADOW).a("▌").end() + "   ",
                ansi.p(FG_WHITE).a("   ▝").p(SHADOW).a("▀▀▀▘").end() + "   "
        };
    }

}
