import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("--- Car Park Simulator ---");
		System.out.println("Enter the capacity of your car park:");
		int capacity = scan.nextInt();
		System.out.println("Enter the rate of arrival (in milliseconds):");
		int arrivalDelay = scan.nextInt();
		System.out.println("Enter the rate of departure (in milliseconds):");
		int departureDelay = scan.nextInt();
		System.out.println("Starting simulation...");
		scan.close();
		
		CarParkControl c = new CarParkControl(capacity);
		
		Thread arrivals = new Thread(new Arrivals(c, arrivalDelay));
		Thread departures = new Thread(new Departures(c, departureDelay));
		
		arrivals.start();
		departures.start();
		
		while(true){
			int numCars = c.getNumCars();
			for(int i = 0; i < numCars; i++){
				System.out.print(" C ");
			}
			for(int i = 0; i < (capacity - numCars); i++){
				System.out.print(" _ ");
			}
			System.out.println("");
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
