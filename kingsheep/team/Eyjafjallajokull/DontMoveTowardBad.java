package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class DontMoveTowardBad implements Algorithm {
	public String getName() {
		return "DontMoveTowardBad";
	}

	double d = 0.0f;
public double getMultiplyer() {
	return d;
}

	public int[] calculate(Type map[][], AI parent) {
		int[] a;

		if(parent.badFields.size() > 0) {
			Field f = parent.badFields.get(0);
			System.out.println("y: " + parent.y + " x: " + parent.x);
			PathAStar path = new PathAStar(parent.y, parent.x, f.y, f.x, map);
			a = path.getDirection();

			for(int i = 0; i < a.length; i++) {
				a[i] = a[i] * -1;
				//if(a[i] == 0)
				//	a[i] = 100;
			}
			if( path.getDistance() != 0)
				d = 10/path.getDistance();
			return a;
		}

		return new int[5];
	}
}
