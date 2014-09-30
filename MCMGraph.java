/**
 * @author Grant Peltier (gfp237)
 * Maximal Cardinality Matching Graph class to construct MCM Graphs
 */

package Assignment1_360C;

import java.util.ArrayList;

public class MCMGraph {
	
	//Attributes
	private ArrayList<Tic> remainingTics;
	private ArrayList<Tac> remainingTacs;
	private ArrayList<Edge> edges;
	private int weight;
	
	//Constructors
	public MCMGraph(Tic[] tics, Tac[] tacs){
		remainingTics = new ArrayList<Tic>();
		remainingTacs = new ArrayList<Tac>();
		for(int k = 0; k < tics.length; k++){
			remainingTics.add(tics[k]);
		}
		for(int k = 0; k < tacs.length; k++){
			remainingTacs.add(tacs[k]);
		}
		edges = new ArrayList<Edge>();
		weight = 0;
	}
	
	//Methods
	public ArrayList<Tic> possibleTics(){
		return remainingTics;
	}
	
	public ArrayList<Tac> possibleTacs(){
		return remainingTacs;
	}
	
	public ArrayList<Edge> currentEdges(){
		return edges;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void addEdge(Edge newEdge){
		edges.add(newEdge);
		remainingTics.remove(newEdge.getTic());
		remainingTacs.remove(newEdge.getTac());
		weight += newEdge.getWeight();
	}
	
	public void removeEdge(Edge badEdge){
		edges.remove(badEdge);
		remainingTics.add(badEdge.getTic());
		remainingTacs.add(badEdge.getTac());
		weight -= badEdge.getWeight();
	}
}
