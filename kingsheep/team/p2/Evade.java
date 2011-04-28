package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class Evade implements Algorithm {
	
	public int[] calculate(Type map[][], AI parent) {
		int a[] = new int[5];

		if(parent.y > 0) {
			if(map[parent.y -1][parent.x] == Type.WOLF2) {
				a[0] = -100;
				a[1] = -100;
			}
		}
		if(parent.y < map.length) {
			if(map[parent.y + 1][parent.x] == Type.WOLF2) {
				a[0] = -100;
				a[2] = -100;
			}
		}
		if(parent.x > 0) {
			if(map[parent.y][parent.x-1] == Type.WOLF2) {
				a[0] = -100;
				a[3] = -100;
			}
		}
		if(parent.x < map.length) {
			if(map[parent.y][parent.x-1] == Type.WOLF2) {
				a[0] = -100;
				a[4] = -100;
			}
		}
		return a;
	}

	public String getName() {
		return "Evade";
	}

	public double getMultiplyer() {
		return 4.0f;
	}
}

