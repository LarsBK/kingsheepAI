package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;
import java.util.ArrayList;

class ClosestGrass implements Algorithm {
   

	public String getName() {
		return "ClosestGrass";
	}
	
	public double getMultiplyer() {
		return 1.0f;
	}

    public int[] calculate(Type map[][], AI parrent){
	return findClosestGrass(map,parrent,1);
    }
    
    private int[] findClosestGrass(Type map[][], Creature parrent, int antsteps){
	int closestfound = 0;
	boolean[][] fieldTested = new boolean[15][19];
	ArrayList outerfield = new ArrayList<String>();
	int toReturn [] = new int[5];

		
	if(((parrent.y+1) < map.length)
	   && (fieldTested[parrent.y+1][parrent.x] != true)){
	    fieldTested[parrent.y+1][parrent.x] = true;
	    if(map[parrent.y+1][parrent.x] == Type.GRASS){
		closestfound = antsteps;
		toReturn[2] = 100;
	    }else{
		String current = parrent.y+1 + "" + parrent.x;
		outerfield.add(current);
	    }
	}
	if((parrent.x +1 < map[0].length)
	   && fieldTested[parrent.y][parrent.x+1] != true){
	    fieldTested[parrent.y][parrent.x+1] = true;
	    if(map[parrent.y][parrent.x+1] == Type.GRASS){
		closestfound = antsteps;
		toReturn[4] = 100;
	    }else{
		String current = parrent.y + "" + parrent.x+1;
		outerfield.add(current);
	    }
	}
	if((parrent.y -1 >= 0)
	   && fieldTested[parrent.y-1][parrent.x] != true){
	    fieldTested[parrent.y-1][parrent.x] = true;
	    if(map[parrent.y-1][parrent.x] == Type.GRASS){
		closestfound = antsteps;
		toReturn[1] = 100;
	    }else{
		String current = parrent.y-1 + "" + parrent.x;
		outerfield.add(current);
	    }
	}
	if(((parrent.x-1) >=0)
	   && fieldTested[parrent.y][parrent.x-1] != true){
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
