package kingsheep.team.Eyjafjallajokull;
import java.util.ArrayList;
import kingsheep.*;



class PathAStarRate implements Path {
	ArrayList<Node> openList = new ArrayList<Node>();
	ArrayList<Node> closedList = new ArrayList<Node>();

	int targetX;
	int targetY;
	Type map[][];
	AI AIparent;

	PathAStarRate(int fromY, int fromX, int toY, int toX, Type m[][], AI p) {
		map = m;
		targetX = toX;
		targetY = toY;
		AIparent = p;

		openList.add(new Node(fromY, fromX, null));
	}

	public int[] getDirection() {
		int a[] = new int[5];
		Node path[];
		Node done = null;

		while(done == null) {
			if(openList.size() <= 0) {
				System.out.println("Path not found");
				return a;
			}
			Node n = getLowest();
			openList.remove(n);
			closedList.add(n);
			done = n.spawn();
		}

		//Found
		path = new Node[done.G+1];

		Node c = done;
		for (int i = path.length-1; i >= 0; i--) {
			path[i] = c;
			c = c.parent;
		}

		if(path[1].y < path[0].y)
			a[1] = 100;
		if(path[1].y > path[0].y)
			a[2] = 100;
		if(path[1].x < path[0].x)
			a[3] = 100;
		if(path[1].x > path[0].x)
			a[4] = 100;

		return a;


	}

	Node getLowest() {
		int highestRate = Integer.MIN_VALUE;
		Node highest = null;

		for(Node n : openList) {
			if(n.totalRate > highestRate)
				highest = n;
				highestRate = n.totalRate;
		}
		return highest;
	}

	class Node {
		Node parent;
		int y;
		int x;

		int G; //Distance to this node
		int H; //Estimated distance to target
		int F; //Estimated total distance to target
		int totalRate; //Rate to this node

		Node(int yi, int xi, Node p) {
			y = yi;
			x = xi;
			parent = p;
			calculate();
		}

		void calculate() {
			if(parent != null) {
				//int rate = AIparent.rateField(y,x);
				//if(rate == 0)
					G = parent.G + 1;
					totalRate = parent.totalRate + 
							AIparent.rateField(y,x) - 1;
				//else if(rate > 0)
				//	G = parent.G -1;
				//else
				//	G = parent.G + 2;
					
			} else
				G = 0;

			H = Math.abs(targetX - x) + Math.abs(targetY - y);
			F = G + H;

		}

		Node spawn() {
			Node n[] = new Node[4];
			n[0] = SpawnHelper(y+1,x);
			n[1] = SpawnHelper(y-1,x);
			n[2] = SpawnHelper(y,x+1);
			n[3] = SpawnHelper(y,x-1);

			for (int i = 0; i<n.length; i++) {
				if(n[i] != null)
					return n[i];
			}
			return null;
		}

		Node SpawnHelper(int yi, int xi) {
			Node n = new Node(yi,xi,this);
			if(AI.isLegal(yi,xi,map) && !closedList.contains(n)) {
				int index = openList.indexOf(n);
				if(index != -1) {
					Node existing = openList.get(index);
					
					if(existing.totalRate < totalRate) {
						existing.parent = this;
						existing.calculate();
						return null;
					}
				}
				openList.add(n);
				if(n.x == targetX && n.y == targetY)
					return n;
				else
					return null;
			}
			return null;
		}


		public boolean equals(Object o) {
			Node n = (Node) o;
			return (x == n.x && y == n.y);
		}
	}
}
