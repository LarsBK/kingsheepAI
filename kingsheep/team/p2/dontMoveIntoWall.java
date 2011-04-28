package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

/* Obsoleted by rateField, do not use*/

class dontMoveIntoWall implements Algorithm {
	public double getMultiplyer() {
		return 2.0f;
	}
	
	public String getName() {
		return "dontMoveIntoWall";
	}

	private boolean legalMoveStolen(int fromX, int fromY,
			int moveToY, int moveToX, Type[][] map) {
		Type t2 = map[moveToY][moveToX];
		Type t1 = map[fromY][fromX];

		if (t2 == Type.FENCE ||
				(t1 == Type.SHEEP1) && (t2 == Type.WOLF1)  ||
				(t1 == Type.SHEEP2) && (t2 == Type.WOLF2)  ||
				(t1 == Type.SHEEP1) && (t2 == Type.SHEEP2) ||
				(t1 == Type.SHEEP2) && (t2 == Type.SHEEP1) ||
				(t1 == Type.WOLF1)  && (t2 == Type.SHEEP1) ||
				(t1 == Type.WOLF1)  && (t2 == Type.WOLF2)  ||
				(t1 == Type.WOLF2)  && (t2 == Type.WOLF1)  ||
				(t1 == Type.WOLF2)  && (t2 == Type.SHEEP2))
			return false;

		return true;
	}

	public int[] calculate(Type map[][], AI parent) {
		int toReturn[] = new int[5];
		int y = parent.y;
		int x = parent.x;

		if(y == 0) {
			toReturn[1] = -100;
		} else if(! legalMoveStolen(y,x,y-1,x,map)) {
			toReturn[1] = -100;
		}

		if(y == map.length-1) {
			toReturn[2] = -100;
		} else if (! legalMoveStolen(y,x,y+1,x,map)) {
			toReturn[2] = -100;
		}

		if(x == 0) {
			toReturn[3] = -100;
		} else if(! legalMoveStolen(y,x,y, x-1,map)) {
			toReturn[3] = -100;
		}

		if(x == map[y].length-1) {
			toReturn[4] = -100;
		} else if(! legalMoveStolen(y,x,y,x+1,map)) {
			toReturn[4] = -100;
		}

		return toReturn;
	}
}
