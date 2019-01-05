package org.kilian.rummy.material;

public enum Color {
    GRAY((char)27 + "[37m"), GREEN((char)27 + "[32m"), MAGENTA((char)27 + "[35m"), BLACK((char)27 + "[107m");

    private final String code;

    Color(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static void main(String[] args) {
        System.out.println(MAGENTA);
    }

    @Override
    public String toString() {
        return code + "Color" + (char)27 + "[0m";
    }
}
