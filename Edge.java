/**
 * @author Grant Peltier (gfp237)
 * Edge class to hold data for edges within MWMCM subgraphs
 */

package Assignment1_360C;

public class Edge {
	
	//attributes
	private Tic tic;
	private Tac tac;
	private int weight;
	
	//constructors
	public Edge(Tic ticID, Tac tacID, int edgeWeight){
		tic = ticID;
		tac = tacID;
		weight = edgeWeight;
	}
	
	//methods
	
	
	public Tic getTic(){
		return tic;
	}
	
	public Tac getTac(){
		return tac;
	}
	
	public int getWeight(){
		return weight;
	}
}
