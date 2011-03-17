package kingsheep.team.test;
import kingsheep.*;

interface Algorithm {

	//Algorithm();

	Move calculate(Type map[][], Creature parent);

	int getRank();

}
