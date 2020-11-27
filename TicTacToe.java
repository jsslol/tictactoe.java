package tictactoe;
/**
 * Jared Schneider
 * Created: 5/3/2018
 * TicTacToe.java
 * The program is a tic tac toe game
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TicTacToe extends JFrame {
  // Indicate which player has a turn, initially it is the X player
  private char whoseTurn = 'X';
  private boolean fin = false;
  private JMenuItem jmiNewGame,jmiExit;

  // Create and initialize cells
  private Cell[][] cells =  new Cell[3][3];

  // Create and initialize a status label
  private JLabel jlblStatus = new JLabel("X's turn to play");

  // Initialize UI
  public TicTacToe() {
    
    // Adds JMenuBar
    JMenuBar jmb = new JMenuBar();
    setJMenuBar(jmb);
    JMenu settingsMenu = new JMenu("File");
    jmb.add(settingsMenu);
    settingsMenu.add(jmiNewGame = new JMenuItem("New Game",'N'));
    settingsMenu.addSeparator();
    settingsMenu.add(jmiExit = new JMenuItem("Exit"));
    
    jmiNewGame.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    
    // Panel p to hold cells
    JPanel p = new JPanel();
    p.setLayout(new GridLayout(3, 3, 0, 0));
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        p.add(cells[i][j] = new Cell());
    
    // Set line borders on the cells panel and the status label
    p.setBorder(new LineBorder(Color.red, 1));
    jlblStatus.setBorder(new LineBorder(Color.yellow, 1));

    // Place the panel and the label to the applet
    add(p, BorderLayout.CENTER);
    add(jlblStatus, BorderLayout.SOUTH);
    
    jmiExit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String actionCommand = e.getActionCommand();
            if(e.getSource() instanceof JMenuItem){
                if("Exit".equals(actionCommand)){
                   System.exit(0);
                }
            }
        }
    });
  }

  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new TicTacToe();
    frame.setTitle("Lab 15A Jared Schneider TicTacToe.java");
    frame.setSize(600, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  // Determine if the cells are all occupied 
  public boolean isFull() {
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (cells[i][j].getToken() == ' ')
          return false;

    return true;
  }

  // Determine if the player with the specified token wins
  public boolean isWon(char token) {
    for (int i = 0; i < 3; i++)
      if ((cells[i][0].getToken() == token)
          && (cells[i][1].getToken() == token)
          && (cells[i][2].getToken() == token)) {
        return true;
      }

    for (int j = 0; j < 3; j++)
      if ((cells[0][j].getToken() ==  token)
          && (cells[1][j].getToken() == token)
          && (cells[2][j].getToken() == token)) {
        return true;
      }

    if ((cells[0][0].getToken() == token)
        && (cells[1][1].getToken() == token)
        && (cells[2][2].getToken() == token)) {
      return true;
    }

    if ((cells[0][2].getToken() == token)
        && (cells[1][1].getToken() == token)
        && (cells[2][0].getToken() == token)) {
      return true;
    }

    return false;
  }

    // An inner class for a cell
    public class Cell extends JPanel{
    // Token used for this cell
    private char token = ' ';

    public Cell() {
      setBorder(new LineBorder(Color.black, 1)); // Set cell's border
      
      //adds mouseListener to register clicks
      addMouseListener(new MouseAdapter(){
        public void mouseClicked(MouseEvent e) {
         if(fin)
             return;
            if (token == ' ') { // If cell is not occupied
                if (whoseTurn == 'X') { // If it is the X player's turn
                    setToken('X');  // Set token in the cell
                    whoseTurn = 'O';  // Change the turn
                    jlblStatus.setText("O's turn");  // Display status
                if (isWon('X')){
                    jlblStatus.setText("X won! The game is over");
                    fin = true;
                }
                else if (isFull()){
                    jlblStatus.setText("Draw! The game is over");
                    fin = true;
                }
        }
            else if (whoseTurn == 'O') { // If it is the O player's turn
                setToken('O'); // Set token in the cell
                whoseTurn = 'X';  // Change the turn
                jlblStatus.setText("X's turn"); // Display status
            if (isWon('O')){
                jlblStatus.setText("O won! The game is over");
                fin = true;
            }
                
            else if (isFull()){
                jlblStatus.setText("Draw! The game is over");
                fin = true;
            }   
        }
      }
     }
    });
      
      //adds action listener to create a new game
      jmiNewGame.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String actionCommand = e.getActionCommand();
            if(e.getSource() instanceof JMenuItem){
                if("New Game".equals(actionCommand)){
                    if (token == ' ')
                        setToken(' ');
                    else if (token == 'X')
                        setToken(' ');
                    else if (token == 'O')
                        setToken(' ');
                    fin = false;
                    jlblStatus.setText("New Game! X's turn");
                    whoseTurn = 'X';
                }
            }
        }
    });
   
    }

    // Return token
    public char getToken() {
      return token;
    }

    // Set a new token 
    public void setToken(char c) {
      token = c;
      repaint();
    }
    
    // Paint the cell
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      if (token == 'X') {
        g.drawLine(10, 10, getSize().width-10, getSize().height-10);
        g.drawLine(getSize().width-10, 10, 10, getSize().height-10);
      }
      else if (token == 'O') {
        g.drawOval(10, 10, getSize().width-20, getSize().height-20);
      }
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
  }
}