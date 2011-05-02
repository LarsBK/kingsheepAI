package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class DontGetKilled implements Algorithm{

	AI parrent; //TYPO lol
	Type[][] map;
	int[] toReturn = new int[5];

	public int[] calculate(Type m[][],AI p){
		parrent = p;
		map = m;
		toReturn = new int[5];

		helper(parrent.y-1,parrent.x,1);
		helper(parrent.y+1,parrent.x,2);
		helper(parrent.y,parrent.x-1,3);
		helper(parrent.y,parrent.x+1,4);

		return toReturn;
	}

	private void helper(int y, int x, int dir) {
		if(parrent.isLegal(y,x,map)) {
			if((map[y][x] != Type.WOLF2)){
				if(calcField(x,y)){
					toReturn[dir] = decrease();
				}
			}else if (map[y][x] == Type.WOLF2){
				toReturn[dir] = decrease();
				toReturn[0] = decrease();
			}
		}
	}

	private int increase(){
		return 100;
	}

	private int decrease() {
		return -100;
	}

	public double getMultiplyer(){
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
		}else if((y - 1 >= 0) && map[y-1][x] == Type.WOLF2){
			toReturn = true;
		}else if((x - 1 >= 0) && map[y][x-1] == Type.WOLF2){
			toReturn = true;
		}

		return toReturn;
	}
}
