
package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class TowardAll implements Algorithm {


	public String getName() {
		return "TowardAll";
	}

	public double getMultiplyer() {
		return 2.0f;
	}

	public int[] calculate(Type map[][], AI parent) {
		int[] a = new int[5];
		for(int i = parent.goodFields.size()-1; i > -1; i--) {
			Field f = parent.goodFields.get(i);
			Path path = new PathAStarRate(parent.y, parent.x, f.y, f.x, map,parent);
			int[] temp = path.getDirection();

			for(int u = 0; u < temp.length; u++) {
				a[u] += (temp[u])*f.getRate();// /(parent.totalGoodRate);/// parent.goodFields.size());
			}
		}

		//find highest
		int highest = Integer.MIN_VALUE;
		for(int i = 0; i < a.length; i++) {
			if(a[i] > highest)
				highest = a[i];
		}

		//find divisor
		double divisor;
		if(highest != 0)
			divisor = highest/100;
		else
			divisor = 1;

		//normalize
		double OMG;
		for(int i = 0; i < a.length; i++) {
			OMG = a[i] / divisor;
			a[i] = (int) OMG;
		}

		return a;
	}

	
} 
