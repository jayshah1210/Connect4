package connect_four_gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

public class Board extends JFrame{

	protected Board b;
	private int player;
	private JPanel Boardpanel;
	private static JLayeredPane layeredGameBoard;
	protected int[][] matrix = new int[6][7];
	private BoardManager bm;
	private String player1name, player2name;
	private Runnable r1;
	private Thread timer;
	private JPanel panel;
	private JTextField timeLabel;
	private JLabel P1Label, P2Label, label;
	private JFrame frame;
	private String s;
	private JButton button1, button2, button3, button4, button5, button6, button7;
	private int currPlayerNum;
	protected JTextField jTextFieldTime = new JTextField();
	
	public Board(Board b) {
			this.b = b;
			bm = new BoardManager(b);
			wel();//accepts the player names and displays it in the main frame
			initialize();//initializes the GUI elements
	}
	
	public Board() {
		
	}

	// pop up window to enter players' names and start the game
	private void wel(){
        player1name = JOptionPane.showInputDialog(null, "Enter Player 1's name", null);
        player2name = JOptionPane.showInputDialog(null, "Enter Player 2's name", null);
        bm.setPlayerName(player1name, player2name);
        timer = new Thread(r1);
        timer.start();
	}
	

	// set up the game window
	private void initialize() {
		
		if (frame != null) frame.dispose();
		frame = new JFrame("Connect 4");
		P1Label = new JLabel("Player 1: " + bm.player1name);
		P2Label = new JLabel("Player 2: " + bm.player2name);
		P1Label.setBounds(120, 620, 200, 20);
		P2Label.setBounds(350, 620, 200, 20);
		frame.getContentPane().add(P1Label);
		frame.getContentPane().add(P2Label);
		
		JLabel labelHeader = new JLabel("Welcome to Connect 4!");
		labelHeader.setBounds(200, 0, 300, 100);
		frame.getContentPane().add(labelHeader);
		
		panel = new JPanel();
		panel.setBounds(20, 0, 954, 100);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		
		s = "1";
		timeLabel = new JTextField("Player " + s + "'s turn");
		timeLabel.setBackground(Color.black);
		timeLabel.setForeground(Color.red);
		timeLabel.setEditable(false);
		timeLabel.setColumns(10);
		timeLabel.setBounds(230, 60, 100, 30);
		frame.getContentPane().add(timeLabel);
		
		timeLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timeLabel.setText(s);
			}
		});
		
		label = new JLabel("Each player has 5 seconds!");
		label.setBounds(20, 20, 200, 100);
		frame.getContentPane().add(label);

		frame.setBounds(300, 50, 560, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		createLayeredBoard();
		createBoard();
		
		frame.getContentPane().setVisible(true);
	}
	
	// create layeredBoard to place disc
	private static JLayeredPane createLayeredBoard() {
		layeredGameBoard = new JLayeredPane();
		layeredGameBoard.setPreferredSize(new Dimension(800, 670));
		
		ImageIcon imageBoard = new ImageIcon(ResourceLoader.load("Images/Board.gif"));
		JLabel imageBoardLabel = new JLabel(imageBoard);

		imageBoardLabel.setBounds(20, 20, imageBoard.getIconWidth(), imageBoard.getIconHeight());
		layeredGameBoard.add(imageBoardLabel, new Integer (0), 1);
		return layeredGameBoard;
	}
			
	// create game board		
	private void createBoard() {
		Boardpanel = new JPanel();
		Boardpanel.setBounds(10, 120, 768, 547);
		Boardpanel.add(layeredGameBoard);
		frame.getContentPane().add(Boardpanel);
		
		button1 = new JButton("1");
		button1.setBounds(10, 88, 80, 35);
		frame.getContentPane().add(button1);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				columnButtonAction(0);
			}
		});
		
		button2 = new JButton("2");
		button2.setBounds(86, 88, 80, 35);
		frame.getContentPane().add(button2);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				columnButtonAction(1);
			}
		});
	
		button3 = new JButton("3");
		button3.setBounds(161, 88, 80, 35);
		frame.getContentPane().add(button3);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				columnButtonAction(2);
			}
		});
	
		button4 = new JButton("4");
		button4.setBounds(237, 88, 80, 35);
		frame.getContentPane().add(button4);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				columnButtonAction(3);
			}
		});
	
		button5 = new JButton("5");
		button5.setBounds(313, 88, 80, 35);
		frame.getContentPane().add(button5);
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				columnButtonAction(4);
			}
		});
	
		button6 = new JButton("6");
		button6.setBounds(389, 88, 80, 35);
		frame.getContentPane().add(button6);
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				columnButtonAction(5);
			}
		});
	
		button7 = new JButton("7");
		button7.setBounds(465, 88, 80, 35);
		frame.getContentPane().add(button7);
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				columnButtonAction(6);
			}
		});
		
	}
	
	
	// start timer and set up the column which the player clicks
	protected void columnButtonAction(int colNum) {
		currPlayerNum = bm.getCurrentPlayer() ? 2 : 1;
		s = Integer.toString(currPlayerNum);
		timeLabel.setText("Player " + s + "'s turn");
		timeLabel.setBackground(Color.black);
		if(currPlayerNum == 2)
			timeLabel.setForeground(Color.yellow);
		else
			timeLabel.setForeground(Color.red);
		Runnable r1 = new Time(currPlayerNum);
		timer.interrupt();
		timer = new Thread(r1);
		timer.start();
		
		bm.setColumnNum(colNum);
	}

	BoardManager bmm = new BoardManager();
	@SuppressWarnings("static-access")
	protected void display(int i, int p) {
		
		jTextFieldTime.setText("" + i);

		if (i == 0) {
			System.out.println("Time's Up! Player " + p + " lost!");
			if(bmm.winner == 0) {
				if(p == 1) bmm.replay(2);
				else bmm.replay(1);
			}
		} else {
			System.out.println("Time: "+ i + "; Player " + p + "'s turn");
		}
	}
	

	//places the respective yellow/red disc according to the player move
	protected void placeChecker(int player, int row, int col) {
		
		setPlayer(player);
		String color;
		
		if (player == 1) color = "RED";
		else color = "YELLOW";
		
		int xOffset = 75 * col;
		int yOffset = 75 * row;
		
		ImageIcon checkerIcon = new ImageIcon(ResourceLoader.load("Images/" + color + ".gif"));
		JLabel checkerLabel = new JLabel(checkerIcon);
		checkerLabel.setBounds(27 + xOffset, 27 + yOffset, checkerIcon.getIconWidth(), checkerIcon.getIconHeight());
		layeredGameBoard.add(checkerLabel, new Integer(0), 0);
		paint(getGraphics());
	}

	public void setPlayer(int player) {
		this.player = player;
	}
	
	public int getPlayer() {
		return player;
	}
}