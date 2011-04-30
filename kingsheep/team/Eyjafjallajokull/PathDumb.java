package kingsheep.team.Eyjafjallajokull;

class PathDumb implements Path {
	int fromX, fromY, targetY, targetX;
	
	PathDumb(int fy, int fx, int ty, int tx) {
		fromX = fx;
		fromY = fy;
		targetY = ty;
		targetX = tx;
	}

	public int[] getDirection() {
		int[] a = new int[5];

		if(targetY < fromY)
			a[1] = 100;
		else if(targetY > fromY)
			a[2] = 100;
		
		if(targetX < fromX)
			a[3] = 100;
		else if(targetX > fromX)
			a[4] = 100;

		return a;
	}

	public int getDistance() {
		return Math.abs(targetY - fromY) + Math.abs(targetX - fromX);
	}
}
