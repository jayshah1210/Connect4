package connect_four_gui;

import javax.swing.JOptionPane;

public class BoardManager{
	
	protected Board b;
	private int input;
	protected static int winner;
	private int player;
	protected boolean playerFlag;
	private Validation v;
	protected static String player1name, player2name;	
	
	public BoardManager(Board b) {
		
		this.b = b;
		startManager();
	}
	
	public BoardManager() {
		
	}
	
	// set up the column that has been clicked 
	public void setColumnNum(int col) {
		this.input = col;
		validate();
	}
	
	public int getColumnNum() {
		return input;
	}

	
	private void startManager() {
		
		player = 1;
		v = new Validation(b);
		winner = 0;
		playerFlag = true;
		
	}
	
	// validate the move
	private void validate() {

		if (winner == 0 || winner == 99 && !v.checkBoardFull()) {
			if(winner != 99)
			{
				if (playerFlag) {
					player = 1;
					playerFlag = false;
				} else {
					player = 2;
					playerFlag = true;
				}
				b.setPlayer(player);
			}
			input = getColumnNum();
			winner = v.updateMove(input, player);
			
			if(winner == 1 || winner == 2) {
				replay(winner);
			}
		}
		if (v.checkBoardFull()) {
			System.out.println("board is full");
			replay(99);
		}
		
	}
	
	
	// set up players names
	protected void setPlayerName(String player1name, String player2name) {
		BoardManager.player1name = player1name;
		BoardManager.player2name = player2name;
	}

	
	// replay the game or quit if a winner is found
	protected void replay(int winner) {
		String msg = "";
		if (winner == 99) {
			msg = "Board is Full!";
		} else if (winner == 1){
			msg = "Player 1 " + player1name + " wins!";
		} else if (winner == 2){
			msg = "Player 2 " + player2name + " wins!";
		}
		
		msg += " Do you want to play again?";
		Object[] choice= {"YES","QUIT"};
		int n = JOptionPane.showOptionDialog(null,
                msg,
                null, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                choice,
                choice[0]);
		if(n == 0) {
			Main.start();
		} else {
			System.exit(0);
		}
	}
	
	
	//To get the current player(1 or 2)
	public boolean getCurrentPlayer() {
		return playerFlag;
	}
}