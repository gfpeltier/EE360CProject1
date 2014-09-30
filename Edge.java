/**
 * @author Grant Peltier (gfp237)
 * Edge class to hold data for edges within MWMCM subgraphs
 */

package Assignment1_360C;

public class Edge {
	
	//attributes
	private int tic;
	private int tac;
	private int weight;
	
	//constructors
	public Edge(int ticID, int tacID, int edgeWeight){
		tic = ticID;
		tac = tacID;
		weight = edgeWeight;
	}
	
	//methods
	public int getTic(){
		return tic;
	}
	
	public int getTac(){
		return tac;
	}
	
	public int getWeight(){
		return weight;
	}
}
