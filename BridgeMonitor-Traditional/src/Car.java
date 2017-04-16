import java.util.Random;

public class Car implements Runnable{
	private String name;
	private int delay;
	private int weight;
	private char lane;
	
	private Bridge bridge;
	
	public Car(String name, int weight, Bridge bridge, char lane){
		this.name = name;
		this.weight = weight;
		this.bridge = bridge;
		this.lane = lane;
		
		Random rnd = new Random();
		delay = 1000 + rnd.nextInt(1001);
	}

	public void run() {
		try {
			if(lane == 'A'){
				bridge.enterLaneA(weight);
				
				System.out.println(name + " entered the bridge on Lane A");
				
				Thread.sleep(delay);
				
				bridge.leaveLaneA(weight);
				
				System.out.println(name + " left the bridge on Lane A");
			}else if(lane == 'B'){
				bridge.enterLaneB(weight);
				
				System.out.println(name + " entered the bridge on Lane B");
				
				Thread.sleep(delay);
				
				bridge.leaveLaneB(weight);
				
				System.out.println(name + " left the bridge on Lane B");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
