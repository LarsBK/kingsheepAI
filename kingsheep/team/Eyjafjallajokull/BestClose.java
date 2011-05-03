//Copy of ClosestGrass @ 19:18 03/21/2011
//Uses rateField

package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class BestClose implements Algorithm {
   

	public String getName() {
		return "BestClose";
	}
	
	public double getMultiplyer() {
		return 0.5f; //var 4.0 ClosestGrassFarAway + den her = min
	}

    public int[] calculate(Type map[][], AI parent) {
		int toReturn[] = new int[5];
		toReturn[0] = parent.rateField(parent.y, parent.x);
		toReturn[1] = parent.rateField(parent.y-1, parent.x);
		toReturn[2] = parent.rateField(parent.y+1, parent.x);
		toReturn[3] = parent.rateField(parent.y, parent.x-1);
		toReturn[4] = parent.rateField(parent.y, parent.x+1);
		
		return toReturn;
    }
} 
