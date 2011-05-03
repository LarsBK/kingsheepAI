package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class DontMoveBack implements Algorithm {
	int prevY = 0;
	int prevX = 0;

	public int[] calculate(Type map[][], AI parent) {
		int toReturn[] = new int[5];
		int i = 0;
		
		if(prevX == parent.x && prevY == parent.y)
			i = -1;
		else {
			if(parent.y < prevY)
				i = 2;
			else if(parent.y > prevY)
				i = 1;
			else if(parent.x < prevX)
				i = 4;
			else if(parent.x > prevX)
				i = 3;
		}
		prevX = parent.x;
		prevY = parent.y;
		
		if(i != -1)
			toReturn[i] = -100;
		return toReturn;
	}

	public double getMultiplyer() {
		return 0.5f;
	}

	public String getName() {
		return "DontMoveBack";
	}
}
