package josep42ny.terminoes;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputHandler {

    public int askPiece(int[] constraints) {
        String in;

        while (true) {
            in = System.console().readLine("Sel·lecciona una peça: ");

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
        throw new NumberFormatException("int does not fit constraints");
    }

    public void waitPlayerSwap() {
        Ansi.clearScreen();
        System.console().readLine("Cambieu jugadors i premeu qualsevol tecla");
        Ansi.clearScreen();
    }

    public void waitKeyPress() {
        System.console().readLine("Continuar?");
    }

}
