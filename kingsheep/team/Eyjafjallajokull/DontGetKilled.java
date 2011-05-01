package kingsheep.team.Eyjafjellajokull;
import kingsheep.*;

class DontGetKilled implements Algorithm{

    public int[] calculate(Type map[][],AI parent){
	int[] toReturn = new int[5];
	boolean curDangerous = false;
	if(((parrent.y+1) < map.length) && (map[parrent.y + 1][parrent.x] != Type.WOLF2)){
	    if(calcField(parrent.x,(parrent.y+1))){
		toReturn[2] = increase();
	    }
	}else{
	    toReturn[2] = increase();
	    toReturn[0] = increase();
	}

	if(((parrent.x + 1) < map[0].length) && (map[parrent.y][parrent.x +1] != Type.WOLF2)){
	    if(calcField((parrent.x + 1),parrent.y)){
		toReturn[4] = increase();
	    }
	}else{
	    toReturn[4] = increase();
	    toReturn[0] = increase();
	}

	if(((parrent.y-1) < 0) && (map[parrent.y - 1][parrent.x] != Type.WOLF2)){
	    if(calcField((parrent.x),(parrent.y -1))){
		toReturn[1] = increase();
	    }
	}else{
	    toReturn[1] = increase();
	    toReturn[0] = increase();

	}

	if(((parrent.x-1) < 0) && (map[parrent.y][parrent.x - 1] != Type.WOLF2)){
	    if(calcField((parrent.x - 1),parrent.y)){
		toReturn[3] = increase();
	    }
	}else{
	    toReturn[3] = increase();
	    toReturn[0] = increase();
	}
	return toReturn;
    }

    private int increase(){
	return 100;
    }

    public double getMultiPlyer(){
	return (parrent.protectSheep/10);
    }

    public String getName(){
	return "DontGetKilled";
    }

    private boolean calcField(int x, int y){
	boolean toReturn = false;
	if(((y + 1) < map.length) && map[y+1][x] == Type.WOLF2){
	    toReturn = true;
	}else if((x + 1 < map[0].length) && map[y][x+1] == Type.WOLF2){
	    toReturn = true;
	}else if((y - 1 >= 0) && map[y-1][x+1] == Type.WOLF2){
	    toReturn = true;
	}else if((x - 1 >= 0) && map[y][x-1] == Type.WOLF2){
	    toReturn = true;
	}

	return toReturn;
    }
}
