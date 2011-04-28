//Finds best possible path using bruteforce

package kingsheep.team.Eyjafjallajokull;
import kingsheep.*;

class BestPossible implements Algorithm {

	Type[][] map;
	AI parent;

	public String getName() {
		return "BestPossible";
	}

	public double getMultiplyer() {
		return 1.0f;
	}

	public int[] calculate(Type m[][], AI p) {
		int toReturn[] = new int[5];
		map = m;
		parent = p;

		BestPath path = new BestPath(p.y, p.x);
		//toReturn[path.calculate()] = 100;
		return path.calculate(); //toReturn;

	}


	class BestPath {

		Node[][] nodes;
		Node n;

		BestPath(int x, int y) {
			nodes = new Node[map.length][map[0].length];
			n = new Node(x,y,null,0);
			nodes[x][y] = n;
		}

		int[] calculate() {
			n.grow();
			return n.h;
		}


		class Node {

			Node p;
			int x, y;
			int rate;
			int dir;
			int[] h;

			Node(int xi, int yi, Node n, int r) {
				x = xi;
				y = yi;
				p = n;
				rateSelf(r);
				nodes[x][y] = this;
			}

			void rateSelf(int r) {
				rate = r + parent.rateField(x,y) - 1;
			}

			int grow() {
				h = new int[5];
				h[1] = tryGrow(x-1,y);
				h[2] = tryGrow(x+1,y);
				h[3] = tryGrow(x,y-1);
				h[4] = tryGrow(x,y+1);

				/*for(int i = 0; i < map.length; i++) {
					for(int u = 0; u < map[0].length; u++) {
						if(nodes[i][u] == null)
							System.out.print(1);
						else
							System.out.print(" ");
					}
					System.out.println();
				}*/


				int b = 0;
				for(int i = 0; i < h.length; i++) {
					if(h[i] > h[b])
						b = i;
				}
				dir = b;
				return h[b];// + h[1] + h[2] + h[3] + h[4];
			}

			int tryGrow(int xi, int yi) {
				if(parent.isLegal(xi,yi,map)) {
					Node n = nodes[xi][yi];

					if(n != null) {
						if(n.p != null) {
							if(n.p.rate >= rate) {
								//other path is best, do nothing
								return rate;
							} else {
								if(!isGrandParent(n)) {
									//this path is better, replace other path
									n.p = this;
									n.rateSelf(rate);
									return n.grow();
								} else
									return 0;
							}
						} else
							return 0;
					} else {
						//new path
						n = new Node(xi,yi,this,rate);
						//nodes[xi][yi] = n;
						return n.grow();
					}
					//n.grow();
				} else {
					return 0;
				}
			}

			boolean isGrandParent(Node n) {

				Node current = this;

				while(current != null) {
					current = current.p;

					if(current == n) {
						return true;
					}
				}

				return false;

			}
		}

	}
}
