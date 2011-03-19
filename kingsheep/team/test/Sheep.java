package kingsheep.team.test;

import kingsheep.*;

public class Sheep extends Creature {

	public Sheep(Type type, Simulator parent, int playerID, int x, int y) {
		super(type, parent, playerID, x, y);
		a[0] = new Random();
		a[1] = new ClosestGrass();
		a[2] = new dontMoveIntoWall();
		a[3] = new keepSameDirection();
	}

	Algorithm a[] = new Algorithm[4];
	//private Move prevMove = Move.WAIT;
	//public Move get() {
	//	return move;
	//}

	protected void think(Type map[][]) {
		//System.out.println(map.length);
		//System.out.println("Is at: " + y + ", " + x);
		//if(true)
		//	throw new RuntimeException("LOL");

		//int voteUp = 0;
		//int voteDown = 0;
		//int voteRight = 0;
		//int voteLeft = 0;

		int vote[] = new int[5];
		int result[];

		for(int i = 0; i < a.length; i++) {
			try{
				result = a[i].calculate(map, this);

				System.out.print(a[i].getName());
				for(int u = 0; u < result.length; u++) {
					vote[u] += a[i].getMultiplyer() * result[u];
					System.out.print("][" + result[u]);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println();

		}
		System.out.println();

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

		//prevMove = move;


		/*rateField(y,x+1, map);
		  int t = (int)(Math.random() * 4);

		  switch (t) {
		  case 0:
		  move = Move.UP;
		  break;
		  case 1:
		  move = Move.DOWN;
		  break;
		  case 2:
		  move = Move.LEFT;
		  break;
		  case 3:
		  move = Move.RIGHT;
		  break;
		  default:
		  move = Move.WAIT;
		  break;
		  }
		 */

		System.out.println("move: " + move);
	}

	private int rateField(int y, int x, Type map[][]) {
		//0 er nøytral
		int rate = 0;

		if(map[y][x] == Type.GRASS) {
			rate+=5;
		} else if(map[y][x] == Type.RHUBARB) {
			rate+=15;
		} else if(map[y][x] == Type.SHEEP2) {
			rate-=100;
		} else if(map[y][x] == Type.WOLF2) {
			rate-=10000;
		} else if(map[y][x] == Type.WOLF1) {
			rate-=-100;
		} else if(map[y][x] == Type.EMPTY) {
			rate += 0;
		} else {
			System.out.println("wtf?");
		}

		if(map[y][x] != Type.EMPTY)
			System.out.println(map[y][x] + " rate: " + rate);
		return rate;
	}
}
