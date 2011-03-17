package kingsheep.team.test;

import kingsheep.*;

public class Sheep extends Creature {

    public Sheep(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);
		a[0] = new Random();
		a[1] = new ClosestGrass();
    }

	Algorithm a[] = new Algorithm[2];

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
			result = a[i].calculate(map, this);

			for(int u = 0; u < result.length; u++) {
				vote[u] += result[u];
				System.out.print(" " + vote[u]);
			}
		}
		System.out.println();

		move = Move.WAIT;

		if(vote[1] > vote[0])
			move = Move.UP;
		if(vote[2] > vote[1])
			move = Move.DOWN;
		if(vote[3] > vote[2])
			move = Move.LEFT;
		if(vote[4] > vote[3])
			move = Move.RIGHT;


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

		System.out.println("Is at: " + y + ", " + x);
		System.out.println("move: " + move + "X:" + y + " Y:" + x);
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
