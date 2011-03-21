package kingsheep.team.Eyjafjallajokull;

import kingsheep.*;


abstract class AI extends Creature {

	//Feelings -100 - 100
	int eatGrass;
	int eatSheep;
	int protectSheep;
	int protectGrass;

	Type map[][];

	//Algorithms
	Algorithm a[];

	AI(Type t, Simulator p, int id, int x, int y) {
		super(t, p, id, x, y);
	}

	protected void think(Type map[][]) {
		this.map = map;
		System.out.println(this.getClass().getName());

		int vote[] = new int[5];
		int result[];
		long thinkTime = 0;

		for(int i = 0; i < a.length; i++) {
			long time = System.nanoTime();
			try{
				result = a[i].calculate(map, this);

				long totalTime = System.nanoTime() - time;

				System.out.print(a[i].getName() + "(" + totalTime + "ns): ");
				thinkTime += totalTime;
				
				for(int u = 0; u < result.length; u++) {
					vote[u] += a[i].getMultiplyer() * result[u];
					System.out.print("|" + result[u]);
				}
			} catch(Exception e) {
				System.err.println(a[i].getName() + " has crashed");
				e.printStackTrace();
				//hang
				while(true) {}
			}
			int in = (int) (a[i].getMultiplyer()*100);
			double m = (double)in / 100;
			System.out.println(" *" + m);
		}

		//move = Move.WAIT;
		int highest = Integer.MIN_VALUE;

		if(vote[0] > highest) {
			highest = vote[0];
			move = Move.WAIT;
		}
		if(vote[1] > highest) {
			highest = vote[1];
			move = Move.UP;
		}
		if(vote[2] > highest) {
			highest = vote[2];
			move = Move.DOWN;
		}
		if(vote[3] > highest) {
			highest = vote[3];
			move = Move.LEFT;
		}
		if(vote[4] > highest) {
			highest = vote[4];
			move = Move.RIGHT;
		}

		System.out.println("move: " + move + " Time: " + thinkTime + "ns\n");
	}

	abstract public int rateField(int y, int x);
}
