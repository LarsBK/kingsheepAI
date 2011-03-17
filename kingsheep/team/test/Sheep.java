package kingsheep.team.test;

import kingsheep.*;

public class Sheep extends Creature {

    public Sheep(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);
    }

    protected void think(Type map[][]) {
		System.out.println(map.length);
		System.out.println("Is at: " + y + ", " + x);
		//if(true)
		//	throw new RuntimeException("LOL");

		rateField(y,x+1, map);
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
