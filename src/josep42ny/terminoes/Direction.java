package josep42ny.terminoes;

public enum Direction {
    UP(new int[]{0, -1}),
    RG(new int[]{1, 0}),
    DW(new int[]{0, 1}),
    LF(new int[]{-1, 0});

    private final int[] vector;

    private Direction(int[] vector) {
        this.vector = vector;
    }

    public int[] getVector() {
        return vector;
    }
}
