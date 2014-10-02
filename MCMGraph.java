/**
 * @author Grant Peltier (gfp237)
 * Maximal Cardinality Matching Graph class to construct MCM Graphs
 */

package Assignment1_360C;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class MCMGraph {
	
	//Attributes
	private ArrayList<Tic> remainingTics;
	private ArrayList<Tac> remainingTacs;
	private ArrayList<Edge> edges;
	private ArrayList<Edge> possibleEdges;
	private ArrayList<Edge> attemptedEdges;
	private int weight;
	private int cardinality;
	
	//Constructors
	public MCMGraph(Tic[] tics, Tac[] tacs){
		remainingTics = new ArrayList<Tic>();
		remainingTacs = new ArrayList<Tac>();
		possibleEdges = new ArrayList<Edge>();
		attemptedEdges = new ArrayList<Edge>();
		for(int k = 0; k < tics.length; k++){
			remainingTics.add(tics[k]);
			ArrayList<Edge> ticEdges = tics[k].getPossibleConnect();
			for(int i = 0; i < ticEdges.size(); i++){
				Edge tmp = ticEdges.get(i);
				possibleEdges.add(tmp);
			}
		}
		for(int k = 0; k < tacs.length; k++){
			remainingTacs.add(tacs[k]);
		}
		edges = new ArrayList<Edge>();
		weight = 0;
		cardinality = 0;
	}
	
	public MCMGraph(MCMGraph old){
		remainingTics = new ArrayList<Tic>(old.possibleTics());
		remainingTacs = new ArrayList<Tac>(old.possibleTacs());
		edges = new ArrayList<Edge>(old.currentEdges());
		possibleEdges = new ArrayList<Edge>(old.getPossibleEdges());
		attemptedEdges = new ArrayList<Edge>(old.getAttemptedEdges());
		weight = old.getWeight();
		cardinality = old.getCard();
	}
	
	
	//Methods

	
	public ArrayList<Tic> possibleTics(){
		return remainingTics;
	}
	
	public ArrayList<Tac> possibleTacs(){
		return remainingTacs;
	}
	
	public ArrayList<Edge> getPossibleEdges(){
		return possibleEdges;
	}
	
	public ArrayList<Edge> getAttemptedEdges(){
		return attemptedEdges;
	}
	
	public ArrayList<Edge> currentEdges(){
		return edges;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getCard(){
		return cardinality;
	}
	
	public boolean addEdge(Edge newEdge){
		if(possibleEdges.contains(newEdge) && !attemptedEdges.contains(newEdge)){
			edges.add(newEdge);
			attemptedEdges.add(newEdge);
			possibleEdges.remove(newEdge);
			Iterator<Edge> it = possibleEdges.iterator();
			while(it.hasNext()){
				Edge nextEdge = it.next();
				if((nextEdge.getTic().getId() == newEdge.getTic().getId()) || (nextEdge.getTac().getId() == newEdge.getTac().getId())){
					it.remove();
				}
			}
			weight += newEdge.getWeight();
			cardinality += 1;
			return true;
		}else{return false;}
		
	}
	
	public void organize(){
		Collections.sort(edges, new Comparator<Edge>(){
			public int compare(Edge p1, Edge p2){
				//return p2.getWeight() - p1.getWeight();
				String first = p1.getTic().getId()+":"+p1.getTac().getId();
				String sec = p2.getTic().getId()+":"+p2.getTac().getId();
				return sec.compareTo(first);
			}
		});
	}
	
	public void reorganize(){
		Collections.sort(edges, new Comparator<Edge>(){
			public int compare(Edge p1, Edge p2){
				return p1.getTac().getId() - p2.getTac().getId();
//				String first = p1.getTic().getId()+":"+p1.getTac().getId();
//				String sec = p2.getTic().getId()+":"+p2.getTac().getId();
//				return sec.compareTo(first);
			}
		});
	}
	

}
