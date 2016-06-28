import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


//This tester will show the shop interface. I have added all the actions to buttons here.
//This is showing it standalone, the actual shop will be initialised with the rest of the interface within SharkInterface_Setup.

public class shop_Tester {

	public static SharkShopInterface shopGUI;
	public static SharkToys sharkToys;
	public static SharkFood sharkFood;
	public static SharkBedding sharkBed;
	public static String itemSelection = "Toys";
	public static int curPosition = 0, minPosition = 0, maxPosition = 4;
	
	public static void main(String[] args){
		

		shopGUI = new SharkShopInterface();
		sharkToys = new SharkToys();
		sharkFood = new SharkFood();
		sharkBed = new SharkBedding();
		setupShopInterface();
		
	}
	
 public static void setupShopInterface(){

		 
		 bedItems();
		 foodItems();
		 toyItems();
		 setupLeave();
		 setupCheckout();
		 setupNext();
		 setupPrev();
		 setupBed();
		 setupFood();
		 setupToys();
		
	 }
	 
	 public static void setupLeave(){
		 
		 shopGUI.getLeaveButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
				}
				}); 
	 }
	 
	 public static void setupCheckout(){
		 shopGUI.getCheckoutButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					if (itemSelection.equals("Toys")){
						
							int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to purchase the "  + sharkToys.getName()[curPosition] + " for " + sharkToys.getCost()[curPosition] + " coins?", "Checkout", JOptionPane.YES_NO_OPTION);
							if ( i == 0) {
								JOptionPane.showMessageDialog(null,"Congratulations! You have purchased the " + sharkToys.getName()[curPosition] +".");
								sharkToys.setBought(curPosition, true);
								
							}	
						}		
					
					
					if (itemSelection.equals("Food")){
						
							int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to purchase the "  + sharkFood.getName()[curPosition] + " for " + sharkFood.getCost()[curPosition] + " coins?", "Checkout", JOptionPane.YES_NO_OPTION);
							if ( i == 0) {
								JOptionPane.showMessageDialog(null,"Congratulations! You have purchased the " + sharkFood.getName()[curPosition] +".");
								sharkFood.setBought(curPosition, true);
									
						}
					}
					
					if (itemSelection.equals("Bed")){
						
							int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to purchase the "  + sharkBed.getName()[curPosition] + " for " + sharkBed.getCost()[curPosition] + " coins?", "Checkout", JOptionPane.YES_NO_OPTION);
							if ( i == 0) {
								JOptionPane.showMessageDialog(null,"Congratulations! You have purchased the " + sharkBed.getName()[curPosition] +".");
								sharkBed.setBought(0, false);
								sharkBed.setBought(1, false);
								sharkBed.setBought(2, false);
								sharkBed.setBought(3, false);
								sharkBed.setBought(4, false);
								sharkBed.setBought(curPosition, true);
								
						}
					}
					
					shopGUI.getBalanceLabel().setText("Coins: " );
					
				}
				}); 
	 }
	 
	 public static void setupNext(){
		 shopGUI.getNextButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					incr();
					if (itemSelection.equals("Toys")){
						toyItems();		
					}
					if (itemSelection.equals("Food")){
						foodItems();		
					}
					if (itemSelection.equals("Bed")){
						bedItems();		
					}
				}
				}); 
	 }
	 
	 public static void setupPrev(){
		 shopGUI.getPrevButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (itemSelection.equals("Toys")){
						toyItems();		
					}
					if (itemSelection.equals("Food")){
						foodItems();		
					}
					if (itemSelection.equals("Bed")){
						bedItems();		
					}
					decr();				
				}
				}); 
	 }
	 
	 public static void setupToys(){
		 shopGUI.getToyButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					curPosition = 0;
					itemSelection = "Toys";
					toyItems();	
				}
				}); 
	 }
	 
	 public static void setupFood(){
		 shopGUI.getFoodButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					curPosition = 0;
					itemSelection = "Food";
					foodItems();	
				}
				}); 
	 }
	 
	 public static void setupBed(){
		 shopGUI.getBedButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					curPosition = 0;
					itemSelection = "Bed";
					bedItems();	
				}
				}); 
	 }
	 
	 public static void toyItems(){
			
			switch(curPosition){
			case 0 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[0]); shopGUI.getDescLabel().setText(sharkToys.getName()[0]); shopGUI.getDescArea().setText(sharkToys.getDesc()[0]); break;
			case 1 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[1]); shopGUI.getDescLabel().setText(sharkToys.getName()[1]); shopGUI.getDescArea().setText(sharkToys.getDesc()[1]); break;
			case 2 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[2]); shopGUI.getDescLabel().setText(sharkToys.getName()[2]); shopGUI.getDescArea().setText(sharkToys.getDesc()[2]); break;
			case 3 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[3]); shopGUI.getDescLabel().setText(sharkToys.getName()[3]); shopGUI.getDescArea().setText(sharkToys.getDesc()[3]); break;
			case 4 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[4]); shopGUI.getDescLabel().setText(sharkToys.getName()[4]); shopGUI.getDescArea().setText(sharkToys.getDesc()[4]); break;
			}		
		}
	 
	 public static void foodItems(){
		 
		 switch(curPosition){
			case 0 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[0]); shopGUI.getDescLabel().setText(sharkFood.getName()[0]); shopGUI.getDescArea().setText(sharkFood.getDesc()[0]); break;
			case 1 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[1]); shopGUI.getDescLabel().setText(sharkFood.getName()[1]); shopGUI.getDescArea().setText(sharkFood.getDesc()[1]); break;
			case 2 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[2]); shopGUI.getDescLabel().setText(sharkFood.getName()[2]); shopGUI.getDescArea().setText(sharkFood.getDesc()[2]); break;
			case 3 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[3]); shopGUI.getDescLabel().setText(sharkFood.getName()[3]); shopGUI.getDescArea().setText(sharkFood.getDesc()[3]); break;
			case 4 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[4]); shopGUI.getDescLabel().setText(sharkFood.getName()[4]); shopGUI.getDescArea().setText(sharkFood.getDesc()[4]); break;
			}
	 }
	 
	 public static void bedItems(){
		 
		 switch(curPosition){
			case 0 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[0]); shopGUI.getDescLabel().setText(sharkBed.getName()[0]); shopGUI.getDescArea().setText(sharkBed.getDesc()[0]); break;
			case 1 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[1]); shopGUI.getDescLabel().setText(sharkBed.getName()[1]); shopGUI.getDescArea().setText(sharkBed.getDesc()[1]); break;
			case 2 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[2]); shopGUI.getDescLabel().setText(sharkBed.getName()[2]); shopGUI.getDescArea().setText(sharkBed.getDesc()[2]); break;
			case 3 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[3]); shopGUI.getDescLabel().setText(sharkBed.getName()[3]); shopGUI.getDescArea().setText(sharkBed.getDesc()[3]); break;
			case 4 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[4]); shopGUI.getDescLabel().setText(sharkBed.getName()[4]); shopGUI.getDescArea().setText(sharkBed.getDesc()[4]); break;			
			}
	 }
	 
	 
	 public static void incr(){
			if(curPosition == 4){
				curPosition = minPosition;
			}else{
				curPosition+=1;
			}
			if (itemSelection.equals("Toys")){
				toyItems();		
			}
			if (itemSelection.equals("Food")){
				foodItems();		
			}
			if (itemSelection.equals("Bed")){
				bedItems();		
			}
		}
		
	 public static void decr(){
			if(curPosition == 0){
				curPosition = maxPosition;
			}else{
			curPosition-=1;
			}
			if (itemSelection.equals("Toys")){
				toyItems();		
			}
			if (itemSelection.equals("Food")){
				foodItems();		
			}
			if (itemSelection.equals("Bed")){
				bedItems();		
			}
		}
	
}
