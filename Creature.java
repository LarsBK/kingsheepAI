package kingsheep;

public abstract class Creature {

    protected enum Move { WAIT, UP, DOWN, LEFT, RIGHT }

    public final Type type;
    private Simulator parent;
    public final int playerID;
    public Move move;
    public int x;
    public int y;
    public boolean alive;

    protected Creature(Type type, Simulator parent, int playerID,
                       int x, int y) {
        this.type = type;
        this.parent = parent;
        this.playerID = playerID;
        this.x = x;
        this.y = y;
        alive = true;
    }

    protected Type[][] filter(Type[][] map) {
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                if (playerID == 2) {
                    if (map[i][j] == Type.WOLF2)
                        map[i][j] = Type.WOLF1;
                    else if (map[i][j] == Type.WOLF1)
                        map[i][j] = Type.WOLF2;
                    else if (map[i][j] == Type.SHEEP2)
                        map[i][j] = Type.SHEEP1;
                    else if (map[i][j] == Type.SHEEP1)
                        map[i][j] = Type.SHEEP2;
                }
            }
        }
        return map;
    }

    /** Make a movement plan by setting <code>move</code> to one of type
        <code>Move</code>. */
    protected abstract void think(Type map[][]);

    public boolean isSheep() {
        return type == Type.SHEEP1 || type == Type.SHEEP2;
    }
}
