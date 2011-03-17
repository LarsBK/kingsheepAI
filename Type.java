package kingsheep;

public enum Type {

    EMPTY('.'), GRASS('g'), FENCE('#'), RHUBARB('r'),
        SHEEP1('1'), SHEEP2('3'), WOLF1('2'), WOLF2('4');

    char c;

    private Type(char c) {
        this.c = c;
    }

    public static Type getType(char c) {
        for (Type t : values())
            if (t.c == c)
                return t;
        return null;
    }
}