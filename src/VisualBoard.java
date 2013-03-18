/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twelve.team;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import twelve.team.Piece.Team;



/**
 *
 * @author Matthew
 */
public class VisualBoard extends JFrame implements MouseListener, MouseMotionListener   {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3881233408615887932L;
	/**
     * Creates new form VisualBoard
     */
    public VisualBoard() {
        initComponents();
        resetBoard();
    }
    
    public void paint(Graphics g){
    	super.paintComponents(g);
    	
    	Graphics2D g2 = (Graphics2D) boardPanel.getGraphics();
    	for(int i=0;i<boardPieces.length;i++){
    		for(int j=0;j<boardPieces[i].length;j++){
    			if(boardPieces[i][j] == null)
    				continue;
    			
    			g2.drawImage(boardPieces[i][j].image, boardPieces[i][j].x-boardPieces[i][j].image.getWidth(null)/2, boardPieces[i][j].y-boardPieces[i][j].image.getHeight(null)/2, null);
    		}
    	}
    	//createGrid(boardPanel.getGraphics(), boardPanel.getWidth(), boardPanel.getHeight());
    	//boardBackground.setVisible(false);
    	//boardPanel.repaint();
    }
    
    public void createGrid(Graphics g, int width, int height){
    	//(p.x-49)/62 + "," + (p.y-24)/62
    	int last = -1;
    	g.setColor(Color.red);
    	for(int i=0;i<width;i++){
    		int j = ((i-17)/62);
    		if(j != last){
    			g.drawLine(i, 0, i, height);
    			last = j;
    		}
    	}
    	last = -1;
    	for(int i=0;i<height;i++){
    		int j = ((i+8)/62);
    		if(j != last){
    			g.drawLine(0, i, width, i);
    			last = j;
    		}
    	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */             
    private void initComponents() {
    	
    	try {
    		blackPiece = ImageIO.read(new File("imgs/black_piece.png"));
			whitePiece = ImageIO.read(new File("imgs/white_piece.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to load pieces");
			e.printStackTrace();
		}

        optionsButton = new JButton();
        boardPanel = new JPanel();
        boardBackground = new JLabel();
        helpButton = new JButton();
        newGameButton = new JButton();
        player1Label = new JLabel();
        player2Label = new JLabel();
        currentTurnLabel = new JLabel();
        currentTurn = new JLabel();
        statusScrollPane = new JScrollPane();
        statusTextArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        optionsButton.setText("Options");

        boardPanel.setPreferredSize(new java.awt.Dimension(600, 300));

        try {
			boardBackground.setIcon(new ImageIcon(ImageIO.read(new File("imgs/board.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // NOI18N

        GroupLayout boardPanelLayout = new GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(boardBackground)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, boardPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(boardBackground))
        );

        helpButton.setText("Help");

        newGameButton.setText("New Game");

        player1Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        player1Label.setText("Player 1 Score: x");

        player2Label.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        player2Label.setText("Player 2 Score: x");

        currentTurnLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        currentTurnLabel.setText("Current Turn:");

        currentTurn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        currentTurn.setText("Player 1");

        statusTextArea.setEditable(false);
        statusTextArea.setColumns(20);
        statusTextArea.setLineWrap(true);
        statusTextArea.setRows(5);
        statusTextArea.setText("Starting Game...");
        statusScrollPane.setViewportView(statusTextArea);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(optionsButton)
                    .addComponent(newGameButton)
                    .addComponent(helpButton))
                .addGap(18, 18, 18)
                .addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(currentTurnLabel, GroupLayout.Alignment.TRAILING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(currentTurn)
                                .addGap(18, 18, 18)))
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(player2Label))
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(player1Label)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(statusScrollPane, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newGameButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpButton)
                .addGap(94, 94, 94))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1Label)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(player2Label)
                .addGap(28, 28, 28)
                .addComponent(currentTurnLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentTurn)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statusScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(boardPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        boardPanel.addMouseListener(this);
        boardPanel.addMouseMotionListener(this);
        pack();
    }// </editor-fold>  
    
    public void resetBoard(){
    	gameBoard = new Board();
    	boardPieces = gameBoard.getBoard();
    	for(int i=0;i<boardPieces.length;i++){
    		for(int j=0;j<boardPieces[i].length;j++){
    			if(boardPieces[i][j] == null)
    				continue;
    			Point p = piecePosition(i, j);
    			boardPieces[i][j].x = p.x;
    			boardPieces[i][j].y = p.y;
    			if(boardPieces[i][j].getTeam() == Team.BLACK){
    				boardPieces[i][j].image = blackPiece;
    			} else {
    				boardPieces[i][j].image = whitePiece;
    			}
    		}
    	}
    	
    	repaint();
    }
    
    public Point piecePosition(int y, int x){
    	return new Point(62*x + 49, 62*y + 24);
    }
    
    public Point closestPiece(Point p){
    	if(!boardPanel.contains(p)){
    		System.out.println("Board does not contain point!");
    		return null;
    	}
//    	System.out.println(p);
//    	System.out.println("clicked Point (" + (p.x-49)/62 + "," + (p.y-24)/62 + ")");
//    	//System.out.println("click ("+p.x+","+p.y+")");
//    	Point closest = new Point(0,0);
//    	for(int i=0;i<boardPieces.length;i++){
//    		for(int j=0;j<boardPieces[i].length;j++){
//    			Point piece = piecePosition(i, j);
//    			//System.out.println("piece "+ i+"," + j +" ("+piece.x+","+piece.y+")");
//    			if(distance(piecePosition(closest.x, closest.y), piece) > distance(p, piece)){
//    				closest = new Point(i, j);
//    			}
//    		}
//    	}
    	Point closest = new Point(((p.y+8)/62),((p.x-17)/62));
    	if(boardPieces[closest.x][closest.y] == null){
    		return null;
    	}
    	//System.out.println("closest ("+closest.x+","+closest.y+")");
    	return closest;
    }
    
    public double distance(Point a, Point b){
    	return Math.pow((a.x-b.x), 2) + Math.pow(a.y-b.y, 2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualBoard().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private JLabel boardBackground;
    private JPanel boardPanel;
    private JLabel currentTurn;
    private JLabel currentTurnLabel;
    private JButton helpButton;
    private JButton newGameButton;
    private JButton optionsButton;
    private JLabel player1Label;
    private JLabel player2Label;
    private JScrollPane statusScrollPane;
    private JTextArea statusTextArea;
    private Board gameBoard;
    private Piece[][] boardPieces;
    private BufferedImage blackPiece;
    private BufferedImage whitePiece;
    private Point movingPiece;
    private boolean moving;
    // End of variables declaration                   
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		if(moving){
			boardPieces[movingPiece.x][movingPiece.y].x = e.getX();
			boardPieces[movingPiece.x][movingPiece.y].y = e.getY();
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = e.getPoint();
		if((movingPiece = closestPiece(p)) != null){
			moving = true;
			statusTextArea.setText("Moving Piece");
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		moving = false;
		statusTextArea.setText("Done moving Piece");
		repaint();
	}
}
