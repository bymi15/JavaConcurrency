
public class Bridge {
	
	private final int MAX_TOTAL_WEIGHT = 5000;
	private final int MAX_NET_WEIGHT = 2000;
	
	private int totalWeight;
	private int laneAWeight;
	private int laneBWeight;
	
	synchronized public void enterLaneA(int weight) throws InterruptedException{
		while((totalWeight+weight) >= MAX_TOTAL_WEIGHT) wait();
		while(Math.abs((laneAWeight + weight) -laneBWeight) >= MAX_NET_WEIGHT) wait();
		
		totalWeight += weight;
		laneAWeight += weight;
		notifyAll();
	}
	
	synchronized public void leaveLaneA(int weight) throws InterruptedException{
		while(totalWeight <= 0) wait();
		while(Math.abs((laneAWeight - weight) -laneBWeight) >= MAX_NET_WEIGHT) wait();
		
		totalWeight -= weight;
		laneAWeight -= weight;
		notifyAll();
	}
	
	synchronized public void enterLaneB(int weight) throws InterruptedException{
		while((totalWeight+weight) >= MAX_TOTAL_WEIGHT) wait();
		while(Math.abs(laneAWeight - (laneBWeight + weight)) >= MAX_NET_WEIGHT) wait();
		
		totalWeight += weight;
		laneBWeight += weight;
		notifyAll();
	}
	
	synchronized public void leaveLaneB(int weight) throws InterruptedException{
		while(totalWeight <= 0) wait();
		while(Math.abs(laneAWeight - (laneBWeight - weight)) >= MAX_NET_WEIGHT) wait();
		
		totalWeight -= weight;
		laneBWeight -= weight;
		notifyAll();
	}
}
