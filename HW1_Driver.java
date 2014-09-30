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
		ArrayList<String> output = new ArrayList<String>();
		int maxCard = getMaxCard(tics, tacs);
		if(maxCard < 0){
			System.out.println("ERROR: Could not determine max cardinality");
		}
		
		return output;
	}
	
	
	public static int minElements(Tic[] tics, Tac[] tacs){
		if(tics.length  < tacs.length){
			return tics.length;
		}else{return tacs.length;}
	}
	
	public static int getMaxCard(Tic[] tics, Tac[] tacs){
		int max = -1;
		int absMax = minElements(tics,tacs);
		determineConnections(tics, tacs);	//Finds all possible edges within graph
		
		if(max <= absMax){
			return max;
		}else{return -1;}
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
