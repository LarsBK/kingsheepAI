package kingsheep.team.Eyjafjallajokull;

import kingsheep.*;

public class Sheep extends AI {

	public Sheep(Type type, Simulator parent, int playerID, int x, int y) {
		super(type, parent, playerID, x, y);
		a = new Algorithm[3];
		a[0] = new Random();
		a[2] = new ClosestGrass();
		//a[2] = new dontMoveIntoWall(); unstable
		a[1] = new keepSameDirection();
		//a[2] = new Evade(); unstable
	}


	public int rateField(int y, int x, Type map[][]) {
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
