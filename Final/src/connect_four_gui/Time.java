package connect_four_gui;

public class Time implements Runnable{

	private Board b2 = new Board();
	private int p;
	
	public Time(int player) {
		this.p = player;
	}

	@Override
	public void run() {
		this.runTime();
	}

	private void runTime() {
		int i = 5;	//set the time for a player to place the disc to be 5s 
		boolean run = true;
		while(i >= 0 && run){
			try {
				b2.display(i, p);
				i--;
				Thread.sleep(1000L);
			} catch (InterruptedException e){
				// System.out.println("Interrupt");
				run = false;
			}
		}
	}
	
}