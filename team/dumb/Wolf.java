package kingsheep.team.dumb;

import kingsheep.*;

public class Wolf extends Creature {

    public Wolf(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);
    }

    protected void think(Type map[][]) {
        move = Move.WAIT;
    }
}