public class Main {
	public static void main(String[] args){
		System.out.println("--- Bridge with two lanes ---");
		final int NUMCARSA = 20;
		final int NUMCARSB = 20;
		
		Bridge bridge = new Bridge();
		int carNum = 1;
		
		for(int i = 0; i < NUMCARSA; i++){
			Car c = new Car("Car " + carNum, 500, bridge, 'A');
			new Thread(c).start();
			carNum++;
		}
		for(int i = 0; i < NUMCARSB; i++){
			Car c = new Car("Car " + carNum, 500, bridge, 'B');
			new Thread(c).start();
			carNum++;
		}
	}
}
