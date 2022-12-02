package data;

public class Colors {
    public static final String _default = "\u001B[0m";
    public static final String empty = _default;
    public static final String black = "\u001B[31m";
    public static final String white = "\u001B[32m";
    public static final String possible = "\u001B[33m";

    static {
        System.out.println(_default);
    }

    private Colors() {
    }
}
