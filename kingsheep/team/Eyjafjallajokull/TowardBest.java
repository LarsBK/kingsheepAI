package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class TowardBest implements Algorithm {
   

	public String getName() {
		return "TowardBest";
	}
	
	public double getMultiplyer() {
		return 1.0f;
	}

    public int[] calculate(Type map[][], AI parent) {
		int[] a;
		for(int i = parent.goodFields.size()-1; i > -1; i--) {
			Field f = parent.goodFields.get(i);
			Path path = new PathAStarRate(parent.y, parent.x, f.y, f.x, map,parent);
			a = path.getDirection();
			
			for(int u = 0; u < a.length; u++) {
				if(a[u] != 0)
					return a;
			}
		}

		return new int[5];
    }
} 
