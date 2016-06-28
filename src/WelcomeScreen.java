import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.IOException;

public class WelcomeScreen {
	
	private JFrame welcomescreenJF;
	
	private JPanel homescreenJP, welcomeJP, newgameJP, exitgameJP, footerJP;
	private JButton newgameB, exitgameB;
	
	private JLabel welcomeLB, footerLB;
	
	public WelcomeScreen(){
		
		welcomescreenJF = new JFrame();
		welcomescreenJF.setUndecorated(true);
		welcomescreenJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		welcomeJP = new JPanel();
		
		
		welcomeJP.setBackground(Color.darkGray);
		welcomeJP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		welcomeLB = new JLabel("QUBAGOTCHI: Shark",JLabel.CENTER);
		welcomeLB.setForeground(Color.WHITE);
		welcomeLB.setFont(new Font("Tahoma", Font.BOLD, 18));
		welcomeJP.add(welcomeLB);
		
		
		newgameJP = new JPanel();
		newgameJP.setLayout(new GridLayout());
		newgameJP.setBackground(Color.darkGray);
		newgameJP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		newgameB = new JButton("START");
		newgameB.setFont(new Font("Tahoma", Font.BOLD, 14));
		newgameB.setBackground(Color.GREEN);
		newgameB.setForeground(Color.WHITE);
		newgameB.setFocusPainted(false);
		newgameJP.add(newgameB);
		
		exitgameJP = new JPanel();
		exitgameJP.setLayout(new GridLayout());
		exitgameJP.setBackground(Color.darkGray);
		exitgameJP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		exitgameB = new JButton("EXIT");
		exitgameB.setFont(new Font("Tahoma", Font.BOLD, 14));
		exitgameB.setBackground(Color.red);
		exitgameB.setForeground(Color.WHITE);
		exitgameB.setFocusPainted(false);
		exitgameJP.add(exitgameB);
		
		footerJP = new JPanel();
		footerJP.setLayout(new GridLayout());
		footerJP.setBackground(Color.darkGray);
		footerJP.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		footerLB = new JLabel("Copyright©2014",JLabel.CENTER);
		footerLB.setForeground(Color.WHITE);
		footerJP.add(footerLB);
		
		homescreenJP = new JPanel();
		homescreenJP.setLayout(new BoxLayout (homescreenJP, BoxLayout.Y_AXIS));
		homescreenJP.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		homescreenJP.setBackground(Color.white);
		homescreenJP.add(welcomeJP);
		homescreenJP.add(newgameJP);
		homescreenJP.add(exitgameJP);
		homescreenJP.add(footerJP);
		

		welcomescreenJF.add(homescreenJP);
		welcomescreenJF.setTitle("QUBagotchi: Shark");
		welcomescreenJF.pack();
		
		welcomescreenJF.setLocationRelativeTo(null);
		welcomescreenJF.setResizable(false);
		welcomescreenJF.setVisible(true);
		
		newGame();
	}
	
	public JFrame getWindow(){
		return welcomescreenJF;
	}
	
	public void newGame(){
		
		newgameB.addActionListener(new ActionListener() 
        {
    	  public void actionPerformed(ActionEvent ae)
    	  {
    		  try {
    			  welcomescreenJF.dispose();
		  			 SharkInterface_Setup.main(null);
		  		 } catch (IOException e) {
		  			 e.printStackTrace();
		  		 }	   
          }
    	});
		
		exitgameB.addActionListener(new ActionListener() 
        {
    	  public void actionPerformed(ActionEvent ae)
    	  {
	          welcomescreenJF.dispose();
	          System.exit(0);
	          
          }
    	});
	}

}
