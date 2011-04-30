package kingsheep.team.Eyjafjallajokull;

import kingsheep.*;

public class Wolf extends AI {

    public Wolf(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);

		AI.wolf = this;

		//Only eats grass if enemy is closer
		eatGrass = 100;
		eatSheep = 30;

		//unused?
		protectSheep = 0;
		protectGrass = 0;

		a = new Algorithm[6];

		//Random combined with DontMoveIllegal prevents standing still
		//Good when a block has occured
		//Random should have low priority, DontMoveIllegal high
		a[0] = new Random();
		a[1] = new DontMoveIllegal();

		//TowardAll goes toward highest consentration of good fields
		//Uncertain of priorities of these
		a[2] = new TowardAll();
		//a[3] = new ClosestGrassFarAway(); //BUG
		//a[0] = new TowardBest();

		//This is important!
		//Keeps ai from going back and fourth between two squares
		//Should have low priority
		a[4] = new keepSameDirection();
		a[5] = new DontMoveBack();
		
    }

    public int rateField(int ry, int rx) {
		//0 er nøytral
		int rate;

		if(ry < 0 || ry > map.length-1 || rx < 0 || rx > map[0].length-1) {
			rate= -100;
		}
		else if(map[ry][rx] == Type.GRASS) {
			rate= shouldDestroy(ry,rx) * eatGrass/5;
		} else if(map[ry][rx] == Type.RHUBARB) {
			rate= shouldDestroy(ry,rx) * eatGrass;
		} else if(map[ry][rx] == Type.SHEEP2) {
			rate= eatSheep;
		} else if(map[ry][rx] == Type.WOLF2) {
			rate= - protectSheep;
		} else if(map[ry][rx] == Type.WOLF1) {
			rate= 0;
		} else if(map[ry][rx] == Type.EMPTY) {
			rate= 0;
		} else if(map[ry][rx] == Type.FENCE) {
			rate= -100;
		} else if(map[ry][rx] == Type.SHEEP1) {
			rate= 0;
		}else {
			rate= 0;
			System.out.println("wtf?" + ry + " " + rx + " " + x + " " + y);
		}

		//if(map[ry][rx] != Type.EMPTY)
		//	System.out.println(map[ry][rx] + " rate: " + rate);
		return rate;

	}

	//Should the wolf eat this field?
	private int shouldDestroy(int yi, int xi) {
		if(!AI.sheep.alive)
			return 1;
		
		//Find path from my sheep to this field
		PathAStar fromMySheep = new PathAStar(AI.sheep.y,AI.sheep.x, yi, xi,map);
		int distanceMySheep = fromMySheep.getDistance();

		//Find path from enemy sheep to this field
		PathAStar fromEnemySheep = new PathAStar(enemySheepY,enemySheepX,yi,xi,map);
		int distanceEnemySheep = fromEnemySheep.getDistance();

		//If enemy sheep is closer than our sheep - eat it
		if(distanceEnemySheep < distanceMySheep) {
			return 1;
		} else {
			return 0;
		}

	}

	void feel() {

	}
}
