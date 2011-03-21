package kingsheep.team.Eyjafjallajokull;

import kingsheep.*;

public class Wolf extends AI {

    public Wolf(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);
		a = new Algorithm[1];
		a[0] = new Random();
    }

    public int rateField(int y, int x, Type map[][]) {
		return 0;
	}
}
