import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


import javax.swing.*;

/**
 * 
 * @author Dean Dodds - 40103628
 *
 */


public class SharkShopInterface {

	//All of the objects below are used to layout the shop interface.
	
	private static JFrame shopFrame;
	private static JPanel backPanel, itemPanel, itemPicPanel, descPanel, descTitlePanel;
	public static JLabel shopLabel, itemLabel, descLabel, logoLabel, saleLabel, backLabel, balanceLabel;
	public static JTextArea descArea;
	public static JButton checkoutButton, leaveButton, toyButton, foodButton, bedButton, prevButton, nextButton;
	public static ImageIcon backPic;
	
	//SharkShopInterface Constructor - This creates a JFrame for the main panels to be added to. (backPanel, itemPanel and descPanel).
	//It also adds all elements to the frame and panels.
	//shopFrame is setup first and is given its parameters, then the JPanels are initialised and setup using the methods, addPanels, addButtons, addLabels, addItemComp and addDescComp.
	//Finally shopFrame is set visible.
	public SharkShopInterface() {
		
		shopFrame = new JFrame();
		shopFrame.setTitle("Shark Shop");
		shopFrame.setSize(400,500);
		shopFrame.setLocationRelativeTo(null);
		shopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		shopFrame.setResizable(false);
		
		backPanel = new JPanel();
		itemPanel = new JPanel();
		descPanel = new JPanel();
		
		addPanels();
		addButtons();
		addLabels();
		addItemComp();
		addDescComp();
		shopFrame.setVisible(true);		
	}
	
	//This method adds the main panels to the JFrame and is used in the constructor.
	//The parameters of each panel is setup here.
	public static void addPanels() {
		
		backPanel.setLayout(null);
		backPanel.setBackground(Color.blue);
		backPanel.setMaximumSize(new Dimension(400,500));
		
		backPic = new ImageIcon("image/Shop/ssback.png");
		backLabel = new JLabel(backPic);
		backLabel.setBounds(0, 0 , 400, 500);
		backPanel.add(backLabel);
		
		itemPanel.setBackground(new Color(0,0,0,100));
		itemPanel.setPreferredSize(new Dimension(250,160));
		itemPanel.setLayout(null);
		itemPanel.setBounds(90, 50, 300, 160);
		backLabel.add(itemPanel);
		
		descPanel.setBackground(new Color(255,255,255,100));
		descPanel.setPreferredSize(new Dimension(385,180));
		descPanel.setLayout(null);
		descPanel.setBounds(5, 220, 385, 180);
		backLabel.add(descPanel);
		
		shopFrame.add(backPanel);
		
	}
	
	//This method adds buttons and is used in the constructor.
	//The backPanel holds the toy, food, bedding, checkout and leave buttons.
	public static void addButtons(){
		
		toyButton = new JButton("Toys");
		toyButton.setBounds(5, 90, 80, 30);
		backLabel.add(toyButton);
		
		foodButton = new JButton("Food");
		foodButton.setBounds(5, 135, 80, 30);
		backLabel.add(foodButton);
		
		bedButton = new JButton("Bedding");
		bedButton.setBounds(5, 180, 80, 30);
		backLabel.add(bedButton);
		
		checkoutButton = new JButton("Checkout");
		checkoutButton.setBounds(20, 420, 130, 40);
		backLabel.add(checkoutButton);
		
		leaveButton = new JButton("Leave");
		leaveButton.setBounds(245, 420, 130, 40);
		backLabel.add(leaveButton);
	}
	
	//This method adds labels and is used in the constructor.
	//The backPanel holds the shop and balance labels.
	public static void addLabels(){

		shopLabel = new JLabel("Shark Shop");
		shopLabel.setFont(new Font("High Tower Text", Font.BOLD, 32));
		shopLabel.setForeground(Color.white);
		shopLabel.setBounds(120, 2, 200, 50);
		backLabel.add(shopLabel);
		
		balanceLabel = new JLabel("Coins: ");
		balanceLabel.setBounds(10, 42, 80, 50);
		balanceLabel.setForeground(Color.white);
		backLabel.add(balanceLabel);
		
	}
	
	//This method adds components to the itemPanel and is used in the constructor.
	//The itemPanel holds the prev and next buttons, itemPicPanel and item label.
	public static void addItemComp(){
		
		prevButton = new JButton("<");
		prevButton.setBounds(10, 55, 50, 50);
		prevButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		prevButton.setFocusPainted(false);
		itemPanel.add(prevButton);
		
		nextButton = new JButton(">");
		nextButton.setBounds(240, 55, 50, 50);
		nextButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		nextButton.setFocusPainted(false);
		itemPanel.add(nextButton);
		
		itemPicPanel = new JPanel();
		itemPicPanel.setBackground(Color.cyan);
		itemPicPanel.setBounds(70, 10, 160, 140);
		itemPanel.add(itemPicPanel);
		
		itemLabel = new JLabel();
		itemLabel.setBounds(0, 0, 150, 140);
		itemLabel.setBackground(Color.black);
		itemPicPanel.add(itemLabel);
	}
	
	//This method adds components to the descPanel and is used in the constructor.
	//The itemPanel holds the description textarea, descTitlePanel and description label.
	public static void addDescComp(){
		
		descArea = new JTextArea();
		descArea.setBackground(Color.white);
		descArea.setForeground(Color.black);
		descArea.setBounds(5,50,375,120);
		descArea.setEditable(false);
		descPanel.add(descArea);
		
		descTitlePanel = new JPanel();
		descTitlePanel.setBounds(60, 2, 260, 45);
		descTitlePanel.setBackground(new Color(118,111,154,255));
		descPanel.add(descTitlePanel);
		
		descLabel = new JLabel();
		descLabel.setBackground(Color.cyan);
		descLabel.setFont(new Font("High Tower Text", Font.BOLD, 32));
		descLabel.setForeground(Color.white);
		descLabel.setBounds(0,0,260,30);
		descTitlePanel.add(descLabel);	
	}
	
	//Getter Methods
	
	public JFrame getShopFrame(){
		return shopFrame;
	}
	
	public JButton getLeaveButton(){
		return leaveButton;
	}

	public JLabel getBalanceLabel(){
		return balanceLabel;
	}
	
	public JButton getCheckoutButton(){
		return checkoutButton;
	}
	
	public JLabel getItemLabel(){
		return itemLabel;
	}
	
	public JButton getNextButton(){
		return nextButton;
	}
	
	public JButton getPrevButton(){
		return prevButton;
	}
	
	public JLabel getDescLabel(){
		return descLabel;
	}
	
	public JTextArea getDescArea(){
		return descArea;
	}
	
	public JButton getToyButton(){
		return toyButton;
	}
	
	public JButton getFoodButton(){
		return foodButton;
	}
	
	public JButton getBedButton(){
		return bedButton;
	}
	
}
