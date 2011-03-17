package kingsheep.team.test;
import kingsheep.*;

class Random implements Algorithm {

        public int[] calculate(Type map[][], Creature parent) {
                int t = (int)(Math.random() * 4);
                
                int a[] = new int[5];
                a[t+1] = 1;
                return a;
        }
}

