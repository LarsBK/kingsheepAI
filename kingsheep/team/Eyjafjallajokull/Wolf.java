package kingsheep.team.Eyjafjallajokull;

import kingsheep.*;

public class Wolf extends AI {

    public Wolf(Type type, Simulator parent, int playerID, int x, int y) {
        super(type, parent, playerID, x, y);

		eatGrass = -100;
		eatSheep = 100;
		protectSheep = 0;
		protectGrass = 0;

		a = new Algorithm[1];
		//a[0] = new Random();
		//a[0] = new BestClose();
		//a[2] = new keepSameDirection();
		//a[3] = new DontStandStill();
		//a[4] = new DontMoveBack();
		a[0] = new TowardBest();
    }

    public int rateField(int ry, int rx) {
		//0 er nøytral
		//IKKE FERDIG
		int rate;

		if(ry < 0 || ry > map.length-1 || rx < 0 || rx > map[0].length-1) {
			rate= -100;
		}
		else if(map[ry][rx] == Type.GRASS) {
			rate= eatGrass/5;
		} else if(map[ry][rx] == Type.RHUBARB) {
			rate= eatGrass;
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
}
