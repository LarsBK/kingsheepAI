package kingsheep.team.p2;

import kingsheep.*;
import java.util.ArrayList;
import java.util.Collections;


abstract class AI extends Creature {

	//Feelings -100 - 100
	int eatGrass;
	int eatSheep;
	int protectSheep;
	int protectGrass;

	//Information
	int enemySheepX;
	int enemySheepY;
	int enemyWolfX;
	int enemyWolfY;

	//list of good and bad fields
	ArrayList<Field> goodFields = new ArrayList<Field>();
	ArrayList<Field> badFields = new ArrayList<Field>();


	Type map[][];

	//Algorithms
	Algorithm a[];

	AI(Type t, Simulator p, int id, int x, int y) {
		super(t, p, id, x, y);
	}

	protected void think(Type map[][]) {
		this.map = map;
		System.out.println(this.getClass().getName());
		scanMap();
		feel();

		int vote[] = new int[5];
		int result[];
		long thinkTime = 0;

		for(int i = 0; i < a.length; i++) {
			long time = System.nanoTime();
			try{
				result = a[i].calculate(map, this);

				long totalTime = System.nanoTime() - time;
				double percent = totalTime/10000000;

				System.out.print(a[i].getName() + "(" + percent + "%): ");
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
		double percent = thinkTime / 10000000;
		System.out.println("move: " + move + " Time: " + percent + "%\n");

	}

	abstract public int rateField(int y, int x);

	static boolean isLegal(Type t1, Type t2) {
	
		if (t2 == Type.FENCE ||
				(t1 == Type.SHEEP1) && (t2 == Type.WOLF1)  ||
				(t1 == Type.SHEEP2) && (t2 == Type.WOLF2)  ||
				(t1 == Type.SHEEP1) && (t2 == Type.SHEEP2) ||
				(t1 == Type.SHEEP2) && (t2 == Type.SHEEP1) ||
				(t1 == Type.WOLF1)  && (t2 == Type.SHEEP1) ||
				(t1 == Type.WOLF1)  && (t2 == Type.WOLF2)  ||
				(t1 == Type.WOLF2)  && (t2 == Type.WOLF1)  ||
				(t1 == Type.WOLF2)  && (t2 == Type.SHEEP2))
			return false;

		return true;
	}
	
	static boolean isLegal(int yi, int xi, Type map[][]) {
		if(yi < 0 || yi > map.length-1 || xi < 0 || xi > map[0].length-1) {
			return false;
		} else if (map[yi][xi] == Type.FENCE) {
			return false;
		}else {
			return true;
		}
	}

	private void scanMap() {
		int rate;
		goodFields.clear();
		badFields.clear();

		for (int y = 0; y<map.length; y++) {
			for (int x = 0; x < map[y].length; x++) {
				rate = rateField(y,x);
				if(rate > 0) {
					goodFields.add(new Field(y,x,rate,map[y][x]));
				} else if(rate < 0) {
					badFields.add(new Field(y,x,rate,map[y][x]));
				}
			}
		}
		Collections.sort(goodFields);
		Collections.sort(badFields);

	}

	abstract void feel();
}

class Field implements Comparable<Field>{
	public Type type;
	public int x;
	public int y;
	public Integer rate;

	Field(int yi, int xi, int r, Type t) {
		x = xi;
		y = yi;
		rate = r;
		type = t;
	}

	public int compareTo(Field f) {
		return rate.compareTo(f.rate);
	}

}
