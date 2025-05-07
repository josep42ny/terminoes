package josep42ny.terminoes;

import java.io.Serializable;
import java.util.Locale;

public class InputHandler implements Serializable {

    public int askConstrainedInt(int[] constraints, String prompt) {
        String in;

        while (true) {
            in = System.console().readLine(prompt);

            if (in.isEmpty()) {
                Ansi.clearPreviousLine();
                continue;
            }

            try {
                return parseConstrainedInt(constraints, in);
            } catch (NumberFormatException nfe) {
                Ansi.clearPreviousLine();
            }

        }
    }

    private int parseConstrainedInt(int[] constraints, String in) throws NumberFormatException {
        int out = Integer.parseInt(in);

        for (int constraint : constraints) {
            if (constraint == out) {
                return out;
            }
        }
        throw new NumberFormatException("Int does not fit constraints");
    }

    public void waitPlayerSwap() {
        Ansi.clearScreen();
        System.console().readLine("Canvieu jugadors i premeu qualsevol tecla");
        Ansi.clearScreen();
    }

    public void waitKeyPress() {
        System.console().readLine("Continuar?");
    }

    public boolean askBoolean(String prompt) {
        while (true) {
            String in = System.console().readLine(prompt).toLowerCase();

            if (in.isEmpty()) {
                Ansi.clearPreviousLine();
                continue;
            }

            if (in.equals("y")) {
                return true;
            } else if (in.equals("n")) {
                return false;
            }
        }
    }

    public boolean askLoad() {
        System.out.println("S'han trobat una o mes partides guardades");
        return askBoolean("Vols carregar una partida guardada [y/n]?");
    }

}
