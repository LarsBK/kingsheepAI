package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;


class DontMoveIllegal implements Algorithm {

	AI parent;

	public double getMultiplyer() {
		return 10.0f;
	}
	
	public String getName() {
		return "DontMoveIllegal";
	}

	public int[] calculate(Type map[][], AI p) {
		parent = p;
		int toReturn[] = new int[5];
		int y = parent.y;
		int x = parent.x;

		toReturn[1] = helper(y-1,x);
		toReturn[2] = helper(y+1,x);
		toReturn[3] = helper(y,x-1);
		toReturn[4] = helper(y,x+1);
		return toReturn;
	}

	private int helper(int y, int x) {
		if(!parent.isLegal(y,x,parent.map))
			return -100;
		else if (parent.map[y][x] == Type.SHEEP1 || parent.map[y][x] == Type.WOLF1)
			return -100;
		else if (parent == AI.sheep && parent.map[y][x] == Type.SHEEP2)
			return -100;
		else
			return 0;
	}
}
