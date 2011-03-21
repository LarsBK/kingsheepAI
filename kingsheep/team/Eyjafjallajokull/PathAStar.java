/*import java.util.ArrayList;

class PathAStar implements Path {
	ArrayList<Node> openList = new ArrayList<Node>();
	ArrayList<Node> closedList = new ArrayList<Node>();

	int targetX;
	int targetY;
	Type map[][];

	PathAStar(int fromY, int fromX, int toY, int toX, Type m[][]) {
		map = m;
		openList.add(new Node(fromY, fromX));
		
		Node n = getLowest();
		openList.remove(n);
		closedList.add(n);

	}

	Node getLowest() {
		int lowestF;
		Node lowest;

		for(Node n : openList) {
			if(n.F < lowestF)
				lowest = n;
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
			G = parent.G + 1;
			H = Math.Abs(targetX - x) + Math.Abs(targetY - y);
			F = G + H;
		}

		void Spawn() {
			SpawnHelper(y+1,x);
			SpawnHelper(y-1,x);
			SpawnHelper(y,x+1);
			SpawnHelper(y,x-1);
		}

		void SpawnHelper(int xi, int yi) {
			Node n = new Node(xi,yi,this);
			if(AI.isLegal(yi,xi) && !closedList.contains(n)) {
				Node existing = openList.get(n);
				if(existing != null) {
					if(existing.G > G) {
						existing.parent = this;
						existing.calculate();
						return;
					}
				}
				openList.add(n);
				return;
			}
		return;
		}


		boolean equals(Node n) {
			return (x == n.x && y == n.y);
		}
	}
}*/
