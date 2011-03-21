package kingsheep.team.test;
import kingsheep.*;
import java.lang.Enum;

class keepSameDirection implements Algorithm {
	int prevX = 0;
	int prevY = 0;

	public int[] calculate(Type map[][], Creature parent) {
		int toReturn[] = new int[5];
		int i = 0;
		if(prevX == parent.x && prevY == parent.y)
			i = -1;
		else {
			if(parent.y < prevY)
				i = 1;
			else if(parent.y > prevY)
				i = 2;
			else if(parent.x < prevX)
				i = 3;
			else if(parent.x > prevX)
				i = 4;
		}
		prevX = parent.x;
		prevY = parent.y;
		
		if(i != -1)
			toReturn[i] = 100;
		return toReturn;
	}

	public double getMultiplyer() {
		return 0.1f;
	}

	public String getName() {
		return "keepSameDir";
	}
}
