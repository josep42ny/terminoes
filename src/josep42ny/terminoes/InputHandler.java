package josep42ny.terminoes;

public class InputHandler {

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
