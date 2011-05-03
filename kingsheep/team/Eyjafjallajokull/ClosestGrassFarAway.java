package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;
import java.util.ArrayList;
import java.util.HashMap;

class ClosestGrassFarAway implements Algorithm{
    int closestfound;
    boolean[][] fieldTested;
    ArrayList<String> uncomplieteRoads;
    ArrayList<String> uncomplieteRoadsForIterator;
    ArrayList<String> complieteRoads;
    int antsteps;
    boolean testing;
    AI parrent;
    Type[][] map;
    boolean found;

    protected ClosestGrassFarAway(){
	testing = false;
    }

    public int[] calculate(Type map[][], AI parrent){
	this.map = map;
	if(testing){
	    System.out.println("START calculate-------------------");
	}
	this.parrent = parrent;
	closestfound = 0;
	fieldTested = new boolean[map.length][map[0].length];
	uncomplieteRoads = new ArrayList<String>();
	complieteRoads = new ArrayList<String>();
	antsteps = 0;
	found = false;


	uncomplieteRoads.add((parrent.x + " " + parrent.y));

	do{
	    antsteps++;
	    uncomplieteRoadsForIterator = uncomplieteRoads;
	    uncomplieteRoads = new ArrayList<String>();

	    for(String s : uncomplieteRoadsForIterator){
		RoadOperations r = new RoadOperations(s);
		checkForGrass(map,r.getLastXValue(),r.getLastYValue(),s);
	    }
	}while(closestfound == 0 && !parrent.goodFields.isEmpty() && antsteps < 1000);
	//now gives up if antsteps is more than 1000 - probably not going to find
	//it at all if we've used 1000 steps (dirty fix)

	int[] calHighest = new int[5];
	HashMap<String,String> removeDouble = new HashMap<String,String>();

	for(String s : complieteRoads){
	    RoadOperations r = new RoadOperations(s);
	    removeDouble.put(( r.getLastXValue() + " " + r.getLastYValue()),s);
	}
	for(String s : removeDouble.values()){
	    RoadOperations r = new RoadOperations(s);

	    if(r.getFirstYValue() == (parrent.y +1)){
		calHighest[2]+= 1;
	    }else if(r.getFirstYValue() == (parrent.y -1)){
		calHighest[1]+= 1;
	    }else if(r.getFirstXValue() == (parrent.x +1)){
		calHighest[4]+=1;
	    }else if(r.getFirstXValue() == (parrent.x -1)){
		calHighest[3]+= 1;
	    }else{
		System.out.println("Calculate in ClosestGrassFarAway just returned wrong first move, it did calculate\n first X: " + r.getFirstXValue() + " \n first Y: "+ r.getFirstYValue() + "\n while parrent is standing on \n parrent X: " + parrent.x + "\n parrent Y: " + parrent.y );
	    }

	}    

	if(testing){
	    System.out.println("SLUTT calculate-------------------");
	}
	if(closestfound == 0){
	    return new int[5];
	}else{
	    return //calHighest;
		givePoints(calHighest);
	}
    }
    private void printFieldTested(){
	for(int y = 0; y < fieldTested.length; y++){
	    System.out.println();
	    for(int x = 0; x <fieldTested[0].length; x++){
		if(fieldTested[y][x]){
		    System.out.print("1 ");
		}else{
		    //		    System.out.print(". ");
		    if(map[y][x] == Type.GRASS){
			System.out.print("g ");
		    }else if(map[y][x] == Type.RHUBARB){
			System.out.print("r ");
		    }else if(map[y][x] == Type.FENCE){
			System.out.print("# ");
		    }else{
			System.out.print(". ");
		    }
		}
	    }
	}
    }


    private int[] givePoints(int[] antGrass){
	int highest = Integer.MIN_VALUE;

	for(int i = 0; i < antGrass.length; i++){
	    if(highest < antGrass[i]){
		highest = antGrass[i];
	    }
	}

	long multiply = (100 / highest);
	for(int i = 0; i < antGrass.length; i++){
	    antGrass[i] = Math.round(antGrass[i] * multiply);
	}
	return antGrass;
    }
    /**
     *@Return incorrect valuse
     */
    public double getMultiplyer(){
	return ((1.5));
    }

    public String getName(){
	return "ClosestGrassFarAway";
    }
    /**
     *tries to check all 4 spots around the current spot.
     *
     *@Param currentRoad : is a String consisting of the road untill the spot its testing on. 
     *  if the road was first 12-13 then 12-14 it would be on the form : 12 13 - 12 14
     */
    private void checkForGrass(Type map[][], int xValue, int yValue, String currentRoad){
	if(testing){
	    printFieldTested();
	    System.out.println("START checkForGrass----------------- \nxValue: " + xValue + "\nyvalue: " + yValue + "\ncurrentRoads: " + currentRoad);
	}
	if(testing){
	    System.out.println("nr 1");
	}
	if(((yValue+1) < map.length)
	   && ((fieldTested[yValue+1][xValue] != true) || found)){
	    fieldTested[yValue+1][xValue] = true;
	    spotCheckAndAdd(map,xValue,(yValue + 1),currentRoad);
	}
	if(testing){
	    System.out.println("nr 2");
	}

	if((xValue +1 < map[0].length)
	   && ((fieldTested[yValue][xValue+1] != true) || found)){
	    fieldTested[yValue][xValue+1] = true;
	    spotCheckAndAdd(map,(xValue + 1),yValue,currentRoad);
	}
	if(testing){
	    System.out.println("nr 3");
	}

	if((yValue -1 >= 0)
	   && ((fieldTested[yValue-1][xValue] != true) || found)){
	    fieldTested[yValue-1][xValue] = true;
	    spotCheckAndAdd(map,xValue,(yValue - 1),currentRoad);
	}
	if(testing){
	    System.out.println("nr 4");
	}

	if(((xValue-1) >=0)
	   && ((fieldTested[yValue][xValue-1] != true) || found)){
	    fieldTested[yValue][xValue-1] = true;
	    spotCheckAndAdd(map,(xValue - 1),yValue,currentRoad);
	}
	if(testing){
	    System.out.println("SLUTT checkForGrass \nxValue: " + xValue + "\nyvalue: " + yValue + "\ncurrentRoads: " + currentRoad);
	}

    }

    private void spotCheckAndAdd(Type map[][], int xValue, int yValue,String currentRoad){
	if(testing){
	    System.out.println("START i spotCheckAndAdd -------------------\nxValue " + xValue + "\nyvalue: " + yValue + "\ncurrentRoads: " + currentRoad);
	}
	String current = (currentRoad + "-" + xValue + " " + yValue);
	int fieldRate = parrent.rateField(yValue,xValue);
	if(testing){
	    System.out.println(fieldRate);
	}
	if(fieldRate == 0){
	    uncomplieteRoads.add(current);
	}else if( fieldRate > 0){
	    closestfound = antsteps;
	    complieteRoads.add(current);
	    found = true;
	}


	/*	if(map[yValue][xValue] == Type.EMPTY){
		uncomplieteRoads.add(current);
		}else if(map[yValue][xValue] == Type.GRASS){
		closestfound = antsteps;
		complieteRoads.add(current);
		}else if(map[yValue][xValue] == Type.RHUBARB){
		closestfound = antsteps;
		complieteRoads.add(current);
		}*/
	if(testing){
	    System.out.println("SLUTT i spotCheckAndAdd -------------------\nxValue " + xValue + "\nyvalue: " + yValue + "\ncurrentRoads: " + currentRoad);
	}
    }
    private class RoadOperations{
	final protected int firstXValue;
	final protected int firstYValue;
	final protected int lastXValue;
	final protected int lastYValue;

	/**
	 *@Param currentRoad 
	 *          takes the currentRoad and applies values to the class int values
	 */
	RoadOperations(String currentRoad){
	    if(testing){	   
		System.out.println("START i RoadOpeartions konstruktor------ "+ currentRoad );//------------
	    }
	    String[] fields = currentRoad.split("-");

	    if(testing){
		for (int i = 0; i < fields.length; i ++){
		    System.out.println(fields.length + "lengde, plass " + i + " st?r: " +  fields[i]);
		}
	    }

	    String[] cord;

	    if(fields.length > 1){
		cord = fields[1].split(" ");
	    }else{
		cord = fields[0].split(" ");
	    }
	    firstXValue = Integer.parseInt(cord[0]);
	    firstYValue = Integer.parseInt(cord[1]);

	    cord = fields[fields.length-1].split(" ");
	    lastXValue = Integer.parseInt(cord[0]);
	    lastYValue = Integer.parseInt(cord[1]);
	    if(testing){
		System.out.println("SLUTT i RoadOpeartions konstruktor------ " + currentRoad );//------------
	    }
	}

	protected int getFirstXValue(){
	    return firstXValue;
	}
	protected int getFirstYValue(){
	    return firstYValue;
	}
	protected int getLastXValue(){
	    return lastXValue;
	}
	protected int getLastYValue(){
	    return lastYValue;
	}
    }
}
