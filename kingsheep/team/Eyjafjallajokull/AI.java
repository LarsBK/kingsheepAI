package kingsheep.team.Eyjafjallajokull;

import kingsheep.*;


abstract class AI extends Creature {

	//Feelings -100 - 100
	private int eatGrass;
	private int eatSheep;
	private int protectSheep;
	private int protectGrass;
	private int wolfFear;

	//Algorithms
	Algorithm a[];

	AI(Type t, Simulator p, int id, int x, int y) {
		super(t, p, id, x, y);
	}

	protected void think(Type map[][]) {

		int vote[] = new int[5];
		int result[];

		for(int i = 0; i < a.length; i++) {
			try{
				result = a[i].calculate(map, this);

				System.out.print(a[i].getName() + ": ");
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
			System.out.println();
		}

		move = Move.WAIT;
		int highest = vote[0];

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

		System.out.println("move: " + move);
	}

	abstract public int rateField(int y, int x, Type map[][]);
}
