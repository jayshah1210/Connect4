package connect_four_gui;

public class Validation {
	
	private Board b;
	
	public Validation(Board b) {
		this.b = b;
	}
	
	// find the right row number to place the checker
	protected int getRowNumber(int col) {
		for (int i = 0; i <= 5; i++) {
			if (b.matrix[5 - i][col] == 0) return i;
		}
		return -1;
	}
	
	// check overflow
	protected boolean checkMove(int row, int col, int player) {
		
		if ((row < 0) || (col < 0) || (row > 5) || (col > 6)) {
			return false;
		}
		return true;
	}
	
	// check whether board is full
	protected boolean checkBoardFull() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (b.matrix[i][j] == 0) return false;
				
			}
		}
		return true;
	}
	
	// update the move in matrix and on the board
	protected int updateMove(int col, int player) {
		int row = getRowNumber(col);
		if (row != -1) {
			if (checkMove(row, col, player)) {
				b.matrix[5 - row][col] = player;
				if(player==1) {
					b.placeChecker(1, 5 - row, col);
					if (checkWin(player)) {
						System.out.println("Winner is Player " + player);
						return player;
					}
				} else {
					b.placeChecker(2, 5 - row, col);
					if (checkWin(player)) {
						System.out.println("Winner is " + player);
						return player;
					}
				
				}
			}
		} else {
			System.out.println("column full");
			return 99; // return 99 if column is full
		}
		return 0;
		
	}
	
	// check whether there is a winner
	protected boolean checkWin(int player) {
		// Check for 4 consecutive discs in a row, horizontally.
		for (int i = 5; i >= 0; i--) { //6 rows
			for (int j = 0; j < 4; j++) { 
				if (b.matrix[i][j] == b.matrix[i][j+1]
						&& b.matrix[i][j] == b.matrix[i][j+2]
						&& b.matrix[i][j] == b.matrix[i][j+3]
						&& b.matrix[i][j] != 0) {
					return true;
				}
			}
		}
		
		// Check for 4 consecutive discs in a row, vertically.
		for (int i = 5; i >= 3; i--) {
			for (int j = 0; j < 7; j++) {
				if (b.matrix[i][j] == b.matrix[i-1][j]
						&& b.matrix[i][j] == b.matrix[i-2][j]
						&& b.matrix[i][j] == b.matrix[i-3][j]
						&& b.matrix[i][j] != 0) {
					return true;
				}
			}
		}
		
		// Check for 4 consecutive discs in a row, in descending diagonals.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				if (b.matrix[i][j] == b.matrix[i+1][j+1]
					&& b.matrix[i][j] == b.matrix[i+2][j+2]
					&& b.matrix[i][j] == b.matrix[i+3][j+3] 
					&& b.matrix[i][j] != 0) {
					return true;
				}
			}
		}
				
		// Check for 4 consecutive discs in a row, in ascending diagonals.
		for (int i = 0; i < 6; i++) { //row
			for (int j = 0; j < 7; j++) { //col
				if (checkMove(i - 3, j + 3, player)) {
					if (b.matrix[i][j] == b.matrix[i-1][j+1]
							&& b.matrix[i][j] == b.matrix[i-2][j+2]
							&& b.matrix[i][j] == b.matrix[i-3][j+3] 
							&& b.matrix[i][j] != 0) {
							return true;
						}
				}
				
			}
		}
		
		return false;
		
	}
}