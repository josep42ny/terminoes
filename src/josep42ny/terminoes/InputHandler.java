package josep42ny.terminoes;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputHandler {

    Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int askPiece(int[] constraints) {
        int out;
        while (true) {
            try {
                out = Integer.parseInt(scanner.next());
            } catch (NumberFormatException nfe) {
                continue;
            }

            for (int constraint : constraints) {
                if (constraint == out) {
                    return out;
                }
            }
            // todo delete previous lines
        }
    }

}
