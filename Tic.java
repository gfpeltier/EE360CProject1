package Assignment1_360C;

public class Tic {
		//Attributes
		private int id;
		private int min;
		private int max;
		private int weight;
		
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
}
