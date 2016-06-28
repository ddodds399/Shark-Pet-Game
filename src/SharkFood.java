import javax.swing.ImageIcon;

/**
 * 
 * @author Dean Dodds - 40103628
 *
 */

public class SharkFood implements ShopItem {

	public ImageIcon[] foodPic;
	public String[] foodName, foodDesc;
	public int[] foodCost;
	public Boolean[] foodBought;
	
	//The sharkFood constructor is used here to fill the arrays declared above.
	//The foodPic array is filled with images to be displayed when toys are selected on the shop interface.
	//The foodName array is filled with the names of the items, these will be displayed on the description label.
	//The foodDesc array is filled with the descriptions of the items and shows the user the cost. This is displayed in the textArea on the descPanel.
	//The foodCost array sets the price of each item.
	//Each item foodBought array is set to false, this shows that no items are bought. This is changed when an item is purchased.
	public SharkFood() {
		foodPic = new ImageIcon[5];
		foodPic[0] = new ImageIcon("image/Shop/coral.png");
		foodPic[1] = new ImageIcon("image/Shop/fish.png");
		foodPic[2] = new ImageIcon("image/Shop/slush.png");
		foodPic[3] = new ImageIcon("image/Shop/penguin.png");
		foodPic[4] = new ImageIcon("image/Shop/seal.png");
		
		foodName = new String[5];
		foodName[0] = "Coral";
		foodName[1] = "Fish";
		foodName[2] = "Blue Slushie";
		foodName[3] = "Penguin";
		foodName[4] = "Seal";
		
		foodDesc = new String[5];
		foodDesc[0] = "Let your shark enjoy a nice veggie starter with this coral.\n\nEffects: Once consumed will set hunger to 100.\nOne use only.\n\nCost: 10 Cutlass Coins";
		foodDesc[1] = "This consists of a long, thin piece of fish that is covered\nin breadcrumbs and then cooked.\n\nEffects: Once consumed will set hunger and energy to 100.\nOne use only.\n\nCost: 25 Cutlass Coins";
		foodDesc[2] = "This frozen uncarbonated beverage is made by freezing a\nnon-carbonated juice or other liquid, and it's blue.\n\nEffects: Once consumed will set thirst to 100.\nOne use only.\n\nCost: 80 Cutlass Coins";
		foodDesc[3] = "Penguins are the top choice for sharks' dinner.\nSome refer to this meat as the chicken of the sea.\n\nEffects: Will set hunger, energy and fun to full.\nOne use only\n\nCost: 125 Cutlass Coins";
		foodDesc[4] = "Seal has the texture of steak and tastes like beef liver.\nComes fried with onions.\n\nEffects: Your shark cannot die from starvation.\n\nCost: 450 Cutlass Coins";
		
		foodCost = new int[5];
		foodCost[0] = 10;
		foodCost[1] = 25;
		foodCost[2] = 80;
		foodCost[3] = 125;
		foodCost[4] = 450;
		
		foodBought = new Boolean[5];
		foodBought[0] = false;
		foodBought[1] = false;
		foodBought[2] = false;
		foodBought[3] = false;
		foodBought[4] = false;
	}
	
	//These methods are inherited from the ShopItem interface.
	//Here the getters return the specific arrays for this class.
	//The setter is used when an item is purchased to set bought to true.
	
	public ImageIcon[] getPic(){
		return foodPic;
	}
	
	public String[] getName(){
		return foodName;
	}
	
	public String[] getDesc(){
		return foodDesc;
	}
	
	public int[] getCost(){
		return foodCost;
	}
	
	public Boolean[] getBought(){
		return foodBought;
	}
	
	public void setBought(int i, Boolean nBought){
			foodBought[i] = nBought;
	}
	
	
}
