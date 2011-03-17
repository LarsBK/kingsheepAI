package kingsheep.team.test;
import kingsheep.*;
import java.util.ArrayList;

class ClosestGrass implements Algorithm {
    int closestfound = 0;
    boolean[][] fieldTested = new boolean[15][19];
    ArrayList outerfield;

    public int[] calculate(Type map[][], Creature parrent){
	return findClosestGrass(map,parrent,1);
    }
    
    private int[] findClosestGrass(Type map[][], Creature parrent, int antsteps){
	int toReturn [] = new int[5];
   
	if(fieldTested[parrent.y+1][parrent.x] != true){
	    fieldTested[parrent.y+1][parrent.x] = true;
	    if(map[parrent.y+1][parrent.x] == Type.GRASS){
		closestfound = antsteps;
		toReturn[1] = 100;
	    }else{
		String current = parrent.y+1 + "" + parrent.x;
		outerfield.add(current);
	    }
	}
	if(fieldTested[parrent.y][parrent.x+1] != true){
	    fieldTested[parrent.y][parrent.x+1] = true;
	    if(map[parrent.y][parrent.x+1] == Type.GRASS){
		closestfound = antsteps;
		toReturn[4] = 100;
	    }else{
		String current = parrent.y + "" + parrent.x+1;
		outerfield.add(current);
	    }
	}
	if(fieldTested[parrent.y-1][parrent.x] != true){
	    fieldTested[parrent.y-1][parrent.x] = true;
	    if(map[parrent.y-1][parrent.x] == Type.GRASS){
		closestfound = antsteps;
		toReturn[2] = 100;
	    }else{
		String current = parrent.y-1 + "" + parrent.x;
		outerfield.add(current);
	    }
	}
	if(fieldTested[parrent.y][parrent.x-1] != true){
	    fieldTested[parrent.y][parrent.x-1] = true;
	    if(map[parrent.y][parrent.x-1] == Type.GRASS){
		closestfound = antsteps;
		toReturn[3] = 100;
	    }else{
		String current = parrent.y + "" + (parrent.x-1);
		outerfield.add(current);
	    }
	}
	return toReturn;
    }
}