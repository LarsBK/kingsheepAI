package kingsheep.team.test;
import kingsheep.*;

class ClosestGrass implements Algorithm {
    int closestfound = 0;
    boolean[][] fieldTested = new boolean[15][19];
    ArrayList outerfield;

    int[] calculate(Type map[][], Creature parrent){
	return findClosestGrass();
   }
    
    private int[] findClosestGrass(Type map[][], Creature parrent, int antsteps){
	
   
	if(closestfound){
	    return null;
	}
	if(fieldTested[parrent.y+1][parrent.x] != true){
	    fieldTested[parrent.y+1][parrent.x] = true;
	    if(map[parrent.y+1][parrent.x] == Type.GRASS){
		closestfound = true;
		int[move.UP] = 100;
	    }else{
		String current = parrent.y+1 + "" + parrent.x;
		outerfield.add(current);
	}
	if(fieldTested[parrent.y][parrent.x+1] != true){
	    fieldTested[parrent.y][parrent.x+1] = true;
	    if(map[parrent.y][parrent.x+1] == Type.GRASS){
		closestfound = true;
		int[move.RIGHT] = 100;
	    }else{
		String current = parrent.y + "" + parrent.x+1;
		outerfield.add(current);
	    }
	}
	if(fieldTested[parrent.y-1][parrent.x] != true){
	    fieldTested[parrent.y-1][parrent.x] = true;
	    if(map[parrent.y-1][parrent.x] == Type.GRASS){
		closestfound = true;
		int[move.SOUTH] = 100;
	    }else{
		String current = parrent.y-1 + "" + parrent.x;
		outerfield.add(current);
	    }
	}
	if(fieldTested[parrent.y][parrent.x-1] != true){
	    fieldTested[parrent.y][parrent.x-1] = true;
	    if(map[parrent.y][parrent.x-1] == Type.GRASS){
		closestfound = true;
		int[move.LEFT] = 100;
	    }else{
		String current = parrent.y + "" + parrent.x-1;
		outerfield.add(current);
	    }
	}
    }
}