
package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class TowardAll implements Algorithm {

	double multi = 0.0;

	TowardAll(double m) {
		multi = m;
	}

	public String getName() {
		return "TowardAll";
	}

	public double getMultiplyer() {
		return multi;
	}


	public int[] calculate(Type map[][], AI parent) {
		int[] a = new int[5];
		for(int i = parent.goodFields.size()-1; i > -1; i--) {
			Field f = parent.goodFields.get(i);
			Path path = new PathAStarRate(parent.y, parent.x, f.y, f.x, map ,parent);
			int[] temp = path.getDirection();

			for(int u = 0; u < temp.length; u++) {
				a[u] += (temp[u]/100)*(parent.rateField(f.y,f.x));// /(parent.totalGoodRate);/// parent.goodFields.size());
				//System.out.println("a" + a[u]);
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
		divisor = (double) highest/100.0f;

		//System.out.println("d" + divisor);

		//normalize
		double OMG;
		for(int i = 0; i < a.length; i++) {
			OMG = a[i] / divisor;
			//System.out.println(OMG);
			a[i] = (int) Math.round(OMG);
		}

		return a;
	}

	
} 
