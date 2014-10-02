/**
 * @author Grant Peltier (gfp237)
 * Main Driver class for EE360C HW1 Fall 2014
 */

package Assignment1_360C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;



public class HW1_Driver {
	
	public static Tic[] GraphTics;
	public static Tac[] GraphTacs;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		File input = new File(args[0]);
		String outName = args[0].substring(0, args[0].indexOf('.'));
		outName += ".out";
		File output = new File(outName);
		
		try{
			BufferedReader plain = new BufferedReader(new FileReader(input));
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			
			int lineIndex = 1;
			int numGraphs = 0;
			int nextGraph = 1;
			int[] ticTacCountArr;
			String s = plain.readLine();
			numGraphs = Integer.parseInt(s);
			for(int r = 0; r < numGraphs; r++){
				s = plain.readLine(); // get tic tac count line
				ticTacCountArr = ticTacCount(s);
				nextGraph += ticTacCountArr[0] + ticTacCountArr[1] + 1;
				int ticCount = ticTacCountArr[0];
				GraphTics = new Tic[ticCount];
				for(int k = 0; k < ticCount; k++){
					GraphTics[k] = parseTic(plain.readLine());
				}
				int tacCount = ticTacCountArr[1];
				GraphTacs = new Tac[tacCount];
				for(int k = 0; k < tacCount; k++){
					GraphTacs[k] = parseTac(plain.readLine());
				}
				ArrayList<String> out = findMatchings(GraphTics, GraphTacs);
				String tmp = out.get(0);
				out.remove(0);
				Collections.sort(out, new Comparator<String>(){
					public int compare(String p1, String p2){
						return p1.compareTo(p2);
					}
				});
				out.add(0,tmp);
				for(int k = 0; k < out.size(); k++){
					writer.write(out.get(k));
					writer.write("\n");
				}

			}
			writer.close();
		}catch (FileNotFoundException e) {
            // File not found
            e.printStackTrace();

		}catch (IOException e) {
            // Not able to read line
            e.printStackTrace();
      }

	}
	
	public static int[] ticTacCount(String s){
		int[] count = new int[2];
		int i = 0; // count index. 0 for tic count, 1 for tac count
		String num = "";
		for(int k = 0; k < s.length(); k++){
			char reg = s.charAt(k);
			if(reg != ' ' && k != (s.length() - 1)){
				num += reg;
			}else{
				if(k == (s.length() - 1)){
					num += reg;
				}
				count[i] = (Integer.parseInt(num));
				num = "";
				i++;
			}
		}
		return count;
	}
	
	public static Tic parseTic(String s){
		int[] vars = new int[4];
		
		String num = "";
		int i = 0;
		for(int k = 0; k < s.length(); k++){
			char reg = s.charAt(k);
			if(reg != ' ' && k != (s.length() - 1)){
				num += reg;
			}else{
				if(k == (s.length() - 1)){
					num += reg;
				}
				vars[i] = (Integer.parseInt(num));
				num = "";
				i++;
			}
			
		}
		return new Tic(vars[0], vars[1], vars[2], vars[3]);
	}
	
	public static Tac parseTac(String s){
		int[] vars = new int[2];
		
		String num = "";
		int i = 0;
		for(int k = 0; k < s.length(); k++){
			char reg = s.charAt(k);
			if(reg != ' ' && k != (s.length() - 1)){
				num += reg;
			}else{
				if(k == (s.length() - 1)){
					num += reg;
				}
				vars[i] = (Integer.parseInt(num));
				num = "";
				i++;
			}
			
		}
		return new Tac(vars[0], vars[1]);
	}
	
	
	public static ArrayList<String> findMatchings(Tic[] tics, Tac[] tacs){
		determineConnections(tics, tacs);	//Finds all possible edges within graph
		ArrayList<MCMGraph> matchings = getMatchings(tics, tacs);
		return destroyDuplicates(renderOutput(matchings));
	}
	
	
	public static ArrayList<String> renderOutput(ArrayList<MCMGraph> graphs){
		ArrayList<String> out = new ArrayList<String>();
		out.add(graphs.size() + "");
		for(int k = 0; k < graphs.size(); k++){
			ArrayList<String> graphEdges = new ArrayList<String>();
			for(int i = 0; i < graphs.get(k).currentEdges().size(); i++){
				Edge tmp = graphs.get(k).currentEdges().get(i);
				graphEdges.add(tmp.getTic().getId()+":"+tmp.getTac().getId());   //need to determine a way to sort the edges lexicographically
				
			}
//			Collections.sort(graphEdges, new Comparator<String>(){
//				public int compare(String p1, String p2){
//					return p1.substring(':').compareTo(p2.substring(':'));
//				}
//			});
			String actual = new String();
			for(int i = 0; i < graphEdges.size(); i++){
				actual += graphEdges.get(i)+" ";
			}
			out.add(actual);
		}
		return out;
	}
	
	
	public static int minElements(Tic[] tics, Tac[] tacs){
		if(tics.length  < tacs.length){
			return tics.length;
		}else{return tacs.length;}
	}
	
	public static ArrayList<MCMGraph> getMatchings(Tic[] tics, Tac[] tacs){
		ArrayList<MCMGraph> matchings = new ArrayList<MCMGraph>();
		MCMGraph mcmBase = new MCMGraph(tics, tacs);
		matchings = recMatchFinder(mcmBase, matchings);
		matchings = getMWMCMs(matchings);
		return matchings;
	}
	
	
	public static ArrayList<String> destroyDuplicates(ArrayList<String> matches){
		Iterator<String> it = matches.iterator();
		ArrayList<String> actual = new ArrayList<String>();
		while(it.hasNext()){
			String nextGraph = it.next();
			if(actual.isEmpty()){
				actual.add(nextGraph);
			}else{
				int diffCounter = 0;
				for(int k = 0; k < actual.size(); k++){
					if(actual.get(k).equals(nextGraph)){
						diffCounter++;
					}
				}
				if(diffCounter == 0){
					actual.add(nextGraph);
				}
			}
			
		}
		actual.set(0,(actual.size()-1)+"");
		return actual;
	}
	
	
	public static ArrayList<MCMGraph> getMWMCMs(ArrayList<MCMGraph> matches){
		ArrayList<MCMGraph> mcm = new ArrayList<MCMGraph>();
		int maxCard = 0;
		int maxWeight = 0;
		for(int k = 0; k < matches.size(); k++){
			if(maxCard < matches.get(k).getCard()){
				maxCard = matches.get(k).getCard();
				maxWeight = matches.get(k).getWeight();
			}
		}
		Iterator<MCMGraph> it = matches.iterator();
		while(it.hasNext()){
			MCMGraph nextGraph = it.next();
			if(nextGraph.getCard() != maxCard){
				it.remove();
			}else if(nextGraph.getWeight() < maxWeight){
				it.remove();
			}else if(nextGraph.getWeight() > maxWeight){
				maxWeight = nextGraph.getWeight();
			}
		}
		
		Iterator<MCMGraph> it2 = matches.iterator();
		while(it2.hasNext()){
			MCMGraph nextGraph = it2.next();
			if(nextGraph.getWeight() < maxWeight){
				it2.remove();
			}
		}
		return matches;
	}
	
	public static ArrayList<MCMGraph> recMatchFinder(MCMGraph graph, ArrayList<MCMGraph> list){
		if(graph.getPossibleEdges().isEmpty() && !list.contains(graph)){
			graph.reorganize();
			list.add(graph); 
			return list;
		}
		if(graph.getPossibleEdges().isEmpty() && list.contains(graph)){ return list;}
		for(int k = 0; k < graph.getPossibleEdges().size(); k++){
			MCMGraph newGraph = new MCMGraph(graph);
			newGraph.addEdge(newGraph.getPossibleEdges().get(k));
			list = recMatchFinder(newGraph, list);
		}
		
		return list;
		
	}
	
	public static void determineConnections(Tic[] tics, Tac[] tacs){
		for(int k = 0; k < tics.length; k++){
			Tic currTic = tics[k];
			ArrayList<Edge> possibles = new ArrayList<Edge>();
			for(int i = 0; i < tacs.length; i++){
				if((tacs[i].getId() <= currTic.getMax()) && (tacs[i].getId() >= currTic.getMin())){
					possibles.add(new Edge(currTic, tacs[i], (currTic.getWeight()+tacs[i].getWeight())));
				}
			}
			currTic.setPossibleConnect(possibles);
		}
	}
	

}
