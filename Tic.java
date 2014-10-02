/**
 * @author Grant Peltier (gfp237)
 * Tic class for Tic nodes in "left" partition of graph
 */

package Assignment1_360C;

import java.util.ArrayList;

public class Tic {
		//Attributes
		private int id;
		private int min;
		private int max;
		private int weight;
		private ArrayList<Edge> possibleTacs;
		
		//Constructors
		public Tic(int Tid, int Tmin, int Tmax, int Tweight){
			id = Tid;
			min = Tmin;
			max = Tmax;
			weight = Tweight;
		}
		
		//Getters & Setters
		
		
		public int getId(){
			return id;
		}
		
		public int getMin(){
			return min;
		}
		
		public int getMax(){
			return max;
		}
		
		public int getWeight(){
			return weight;
		}
		
		public ArrayList<Edge> getPossibleConnect(){
			return possibleTacs;
		}
		
		public void setPossibleConnect(ArrayList<Edge> poss){
			possibleTacs = poss;
		}
}
