/**
 * @author Grant Peltier (gfp237)
 * Tac class for Tac nodes in "right" partition of graph
 */

package Assignment1_360C;

public class Tac {
	//Attributes
	private int id;
	private int weight;
	
	//Constructors
	public Tac(int Tid, int Tweight){
		id = Tid;
		weight = Tweight;
	}
	
	//Getters & Setters
	public int getId(){
		return id;
	}
	
	public int getWeight(){
		return weight;
	}
}
