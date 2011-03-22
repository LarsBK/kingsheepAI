package kingsheep.team.test;
import kingsheep.*;
import java.util.ArrayList;

class ClosestGrass implements Algorithm {
   

	public String getName() {
		return "ClosestGrass";
	}
	
	public double getMultiplyer() {
		return 1.0f;
	}

    public int[] calculate(Type map[][], Creature parrent){
	return findClosestGrass(map,parrent);
    }
    
    private int[] findClosestGrass(Type map[][], Creature parrent){
	int toReturn [] = new int[5];

		
	if((parrent.y+1) < map.length){
	    if(map[parrent.y+1][parrent.x] == Type.GRASS){
		toReturn[2] = 100;
	    }
	}
	if((parrent.x +1) < map[0].length){
	    if(map[parrent.y][parrent.x+1] == Type.GRASS){
		toReturn[4] = 100;
	    }
	}
	if((parrent.y -1) >= 0){
	    if(map[parrent.y-1][parrent.x] == Type.GRASS){
		toReturn[1] = 100;
	    }
	}
	if(((parrent.x-1) >=0)){
	    if(map[parrent.y][parrent.x-1] == Type.GRASS){
		toReturn[3] = 100;
	    }
	}
	return toReturn;
    }
}
