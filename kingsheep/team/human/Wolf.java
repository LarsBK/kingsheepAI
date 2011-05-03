package kingsheep.team.Human;

import kingsheep.*;
import java.util.Scanner;

public class Wolf extends Creature {

	public Wolf(Type type, Simulator parent, int playerID, int x, int y) {
		super(type, parent, playerID, x, y);
	}

	protected void think(Type map[][]) {
		move = Move.WAIT;

		Scanner i = new Scanner(System.in);
		String text;

		System.out.print("Wolf: ");
		try {
			text = i.nextLine();
		} catch(Exception e) {
			e.printStackTrace();
			text = "";
		}   
		


		if(text.equals("w")) {
			move = Move.UP;
		}
		if(text.equals("s")) {
			move = Move.DOWN;
		}
		if(text.equals("a")) {
			move = Move.LEFT;
		}
		if(text.equals("d")) {
			move = Move.RIGHT;
		}
	}
}
