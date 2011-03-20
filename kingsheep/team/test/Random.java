package kingsheep.team.test;
import kingsheep.*;

class Random implements Algorithm {
	public String getName() {
		return "Random";
	}

    public int[] calculate(Type map[][], Creature parent) {
                int a[] = new int[5];
				
				for(int i = 1; i < a.length; i++) {
					a[i] = (int)(Math.random() * 100);
				}
                
                return a;
        }

		public double getMultiplyer() {
			return 0.13f;
		}
}

