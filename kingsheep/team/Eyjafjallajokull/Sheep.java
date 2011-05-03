package kingsheep.team.Eyjafjallajokull;

import kingsheep.*;

public class Sheep extends AI {

	public Sheep(Type type, Simulator parent, int playerID, int x, int y) {
		super(type, parent, playerID, x, y);
		
		AI.sheep = this;

		eatGrass = 100;
		eatSheep = 0; //-50; //You generally want to be someplace else than the enemy
		protectSheep = 100; //Stay alive, replaces wolfFear. 100 is terrified of enemy wolf
		protectGrass = 0;

		a = new Algorithm[9];

		//Random combined with DontMoveIllegal prevents standing still
		//Good when a block has occured
		//Random should have low priority, DontMoveIllegal high
		a[0] = new Random();
		a[1] = new DontMoveIllegal();

		//TowardAll goes toward highest consentration of good fields
		//Uncertain of priorities of these
		//a[2] = new TowardAll();
		a[3] = new ClosestGrassFarAway();
		a[4] = new TowardAll();
		a[8] = new BestClose();

		//This is important!
		//Keeps ai from going back and fourth between two squares
		//Should have low priority
		a[5] = new keepSameDirection();
		a[6] = new DontMoveBack();

		//herpderp
		a[7] = new DontGetKilled();


	}

	void feel() {

	}


    public int rateFieldHelp(int ry, int rx) {
		//0 er nøytral
		//IKKE FERDIG
		int rate;

		if(!isLegal(ry,rx, map)) {
			rate= -100;
			return rate;
		}
		else if(map[ry][rx] == Type.GRASS) {
			rate= isCloser(ry,rx) * eatGrass/5;
		} else if(map[ry][rx] == Type.RHUBARB) {
			rate= isCloser(ry,rx) * eatGrass;
		} else if(map[ry][rx] == Type.SHEEP2) {
			rate= eatSheep;
		} else if(map[ry][rx] == Type.WOLF2) {
			rate= - protectSheep;
		} else if(map[ry][rx] == Type.WOLF1) {
			rate= -10;
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

	//Should the sheep eat this field
	private int isCloser(int yi, int xi) {
		if(!AI.sheep.alive)
			return 2;
		
		//Find path from my sheep to this field
		PathAStar fromMySheep = new PathAStar(y,x, yi, xi,map);
		fromMySheep.getDirection();
		int distanceMySheep = fromMySheep.getDistance();

		//Find path from enemy sheep to this field
		PathAStar fromEnemySheep = new PathAStar(enemySheepY,enemySheepX,yi,xi,map);
		fromEnemySheep.getDirection();
		int distanceEnemySheep = fromEnemySheep.getDistance();
		//System.out.println("1 " + distanceEnemySheep);
		//System.out.println("2 " + distanceMySheep);

		if(distanceEnemySheep >= distanceMySheep) {
			return 3;
		} else {
			return 2;
		}

	}
}
