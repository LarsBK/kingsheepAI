package kingsheep.team.Eyjafjallajokull;
import java.util.ArrayList;
import kingsheep.*;



class PathAStarLiteRate implements Path {
	ArrayList<Node> openList = new ArrayList<Node>();
	ArrayList<Node> closedList = new ArrayList<Node>();

	int targetX;
	int targetY;
	Type map[][];
	int distance = 0;
	AI p;

	PathAStarLiteRate(int fromY, int fromX, int toY, int toX, Type m[][], AI pa) {
		map = m;
		p = pa;
		targetX = toX;
		targetY = toY;

		openList.add(new Node(fromY, fromX, null));
		//System.out.println("Target: " + toY + " " + toX);

	}

	public int getDistance() {
		return distance;
	}


	public int[] getDirection() {
		int a[] = new int[5];
		Node path[];
		Node done = null;

		while(done == null) {
			if(openList.size() <= 0) {
				//System.out.println("Path not found");
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

		distance = done.G;

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
		int lowestF = Integer.MAX_VALUE;
		Node lowest = null;

		for(Node n : openList) {
			if(n.F < lowestF)
				lowest = n;
				lowestF = lowest.F;
		}
		return lowest;
	}

	class Node {
		Node parent;
		int y;
		int x;

		int G; //Distance to this node
		int H; //Estimated distance to target
		int F; //Estimated total distance to target

		Node(int yi, int xi, Node p) {
			y = yi;
			x = xi;
			parent = p;
			calculate();
		}

		void calculate() {
			if(parent != null)
				G = parent.G + 1;
			else
				G = 0;

			H = Math.abs(targetX - x) + Math.abs(targetY - y);
			F = G + H;

		}

		Node spawn() {
		//	System.out.println("Distance: " + F);			

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
			if(AI.isLegal(yi,xi,map) && !closedList.contains(n) && p.rateField(yi,xi) > 0) {
				int index = openList.indexOf(n);
				if(index != -1) {
					Node existing = openList.get(index);
					
					if(existing.G > G) {
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
