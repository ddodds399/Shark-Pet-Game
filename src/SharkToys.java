import javax.swing.ImageIcon;

/**
 * 
 * @author Dean Dodds - 40103628
 *
 */

public class SharkToys implements ShopItem {

	public ImageIcon[] toyPic;
	public String[] toyName, toyDesc;
	public int[] toyCost;
	public Boolean[] toyBought;
	
	//The sharkToys constructor is used here to fill the arrays declared above.
	//The toyPic array is filled with images to be displayed when toys are selected on the shop interface.
	//The toyName array is filled with the names of the items, these will be displayed on the description label.
	//The toyDesc array is filled with the descriptions of the items and shows the user the cost. This is displayed in the textArea on the descPanel.
	//The toyCost array sets the price of each item.
	//Each item toyBought array is set to false, this shows that no items are bought. This is changed when an item is purchased.
	public SharkToys(){
		
		toyPic = new ImageIcon[5];
		toyPic[0] = new ImageIcon("image/Shop/ball.png");
		toyPic[1] = new ImageIcon("image/Shop/ring.png");
		toyPic[2] = new ImageIcon("image/Shop/hat.png");
		toyPic[3] = new ImageIcon("image/Shop/ship.png");
		toyPic[4] = new ImageIcon("image/Shop/chest.png");
		
		toyName = new String[5];
		toyName[0] = "Beach Ball";
		toyName[1] = "Rubber Ring";
		toyName[2] = "Pirate Hat";
		toyName[3] = "Toy Boat";
		toyName[4] = "Treasure Chest";
		
		toyDesc = new String[5];
		toyDesc[0] = "Enjoy the summer days even more with this large inflatable ball.\nPerfect for in the ocean or on the beach.\n\nEffects: Playing is now more effective on traits. \n\nCost: 75 Cutlass Coins";
		toyDesc[1] = "This inflatable ring offers your shark an extreme approach to \ninflatable fun!\nA repair patch is included for the likely event of a puncture.\n\nEffects: Swim level goes up on next swim.\n\nCost: 100 Cutlass Coins";
		toyDesc[2] = "Make your shark's enemies walk the plank with this pirate hat.\nMade from high quality, soft velvet, so comfort is assured.\n\nEffects: While scaring tourists, states do not decrease.\n\nCost: 250 Cutlass Coins";
		toyDesc[3] = "Embark on a voyage across the seven seas with this boat.\nMade from 100% recycled plastic milk containers.\n\nEffects: Hunting no longer makes states decrease.\n\nCost: 500 Cutlass Coins";
		toyDesc[4] = "A pirate's chest is great for storing any landlubber’s treasure.\nDisclaimer: May contain cursed treasure or bubbles.\n\nEffects: Find Cutlass Coins more regularly.\n\nCost: 1000 Cutlass Coins";
		
		toyCost = new int[5];
		toyCost[0] = 75;
		toyCost[1] = 100;
		toyCost[2] = 250;
		toyCost[3] = 500;
		toyCost[4]= 1000;
		
		toyBought = new Boolean[5];
		toyBought[0] = false;
		toyBought[1] = false;
		toyBought[2] = false;
		toyBought[3] = false;
		toyBought[4] = false;
	}
	
	//These methods are inherited from the ShopItem interface.
	//Here the getters return the specific arrays for this class.
	//The setter is used when an item is purchased to set bought to true.
	
	public ImageIcon[] getPic(){
		return toyPic;
	}
	
	public String[] getName(){
		return toyName;
	}
	
	public String[] getDesc(){
		return toyDesc;
	}
	
	public int[] getCost(){
		return toyCost;
	}
	
	public Boolean[] getBought(){
		return toyBought;
	}
	
	public void setBought(int i, Boolean nBought){
			toyBought[i] = nBought;
	}
}

