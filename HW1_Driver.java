package Assignment1_360C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class HW1_Driver {
	
	Tic[] GraphTics;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		File input = new File(args[0]);
		File output = new File(args[1]);
		
		try{
			BufferedReader plain = new BufferedReader(new FileReader(input));
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			
			int lineIndex = 0;
			int numGraphs = 0;
			for (String s = plain.readLine(); s != null; s = plain.readLine()){
				if(lineIndex == 0){
					numGraphs = Integer.parseInt(s);
				}
				for(int k = 0; k < s.length(); k++){
					char reg = s.charAt(k);
					String num = "";
					if(reg != ' '){
						num += reg;
					}else{
						
					}
				}
				lineIndex++;
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
	
	

}
