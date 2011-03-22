
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
		if(parent.goodFields.size() > 0) {
			Field f = parent.goodFields.get(parent.goodFields.size() - 1);
			Path path = new PathDumb(parent.y, parent.x, f.y, f.x);
			return path.getDirection();
		}

		return new int[5];
    }
} 
