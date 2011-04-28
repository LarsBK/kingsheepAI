package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class DontMoveTowardBad implements Algorithm {
	public String getName() {
		return "DontMoveTowardBad";
	}

	public int[] calculate(Type map[][], AI parent) {
		int[] a;

		if(parent.badFields.size() > 0) {
			Field f = parent.badFields.get(0);
			Path path = new PathAStarRate(parent.y, parent.x, f.y, f.x, map,parent);
			a = path.getDirection();

			for(int i = 0; i < a.length; i++) {
				a[i] = a[i] * -1;
			}
			return a;
		}

		return new int[5];
	}
}
