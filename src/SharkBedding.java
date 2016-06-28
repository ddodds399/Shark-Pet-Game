import javax.swing.ImageIcon;

/**
 * 
 * @author Dean Dodds - 40103628
 *
 */

public class SharkBedding implements ShopItem {

	public ImageIcon[] bedPic;
	public String[] bedName, bedDesc;
	public int[] bedCost;
	public Boolean[] bedBought;
	
	//The sharkBedding constructor is used here to fill the arrays declared above.
	//The bedPic array is filled with images to be displayed when toys are selected on the shop interface.
	//The bedName array is filled with the names of the items, these will be displayed on the description label.
	//The bedDesc array is filled with the descriptions of the items and shows the user the cost. This is displayed in the textArea on the descPanel.
	//The bedCost array sets the price of each item.
	//Each item bedBought array is set to false, this shows that no items are bought. This is changed when an item is purchased.
	public SharkBedding() {
		bedPic = new ImageIcon[5];
		bedPic[0] = new ImageIcon("image/Shop/rock.png");
		bedPic[1] = new ImageIcon("image/Shop/wreck.png");
		bedPic[2] = new ImageIcon("image/Shop/temple.png");
		bedPic[3] = new ImageIcon("image/Shop/skull.png");
		bedPic[4] = new ImageIcon("image/Shop/at.png");
		
		bedName = new String[5];
		bedName[0] = "Bedrock";
		bedName[1] = "Ship Wreck";
		bedName[2] = "Temple";
		bedName[3] = "Skull Mountain";
		bedName[4] = "Atlantis";
		
		bedDesc = new String[5];
		bedDesc[0] = "Here is a rock, but it's also a bed. Bedrock.\n\nEffects: Energy cannot drop below 20%.\n\nCost: 250 Cutlass Coins";
		bedDesc[1] = "The remains of a once great ship, all it needs is a new tennant.\n\nEffects: Energy and Fun cannot fall below 50%.\n\nCost: 600 Cutlass Coins";
		bedDesc[2] = "Great ocean view, no structural problems, good home.\n\nEffects: Energy, Fun and Social cannot fall below 50%.\n\nCost: 1000 Cutlass Coins";
		bedDesc[3] = "The most expensive evil lair on the market.\nAny aspiring evil genius can carry out their plans here.\n\nEffects: Energy, Fun and Social cannot fall below 75%\nScaring now also resets all bars.\n\nCost: 2500 Cutlass Coins";
		bedDesc[4] = "That's right, claim your own lost city now!\n\nEffects: Invulnerable to health problems. (Can still die of old age)\n\nCost: 6900 Cutlass Coins";
		
		bedCost = new int[5];
		bedCost[0] = 250;
		bedCost[1] = 600;
		bedCost[2] = 1000;
		bedCost[3] = 2500;
		bedCost[4] = 6900;
		
		bedBought = new Boolean[5];
		bedBought[0] = false;
		bedBought[1] = false;
		bedBought[2] = false;
		bedBought[3] = false;
		bedBought[4] = false;
	}
	
	//These methods are inherited from the ShopItem interface.
	//Here the getters return the specific arrays for this class.
	//The setter is used when an item is purchased to set bought to true.
	
	public ImageIcon[] getPic(){
		return bedPic;
	}
	
	public String[] getName(){
		return bedName;
	}
	
	public String[] getDesc(){
		return bedDesc;
	}
	
	public int[] getCost(){
		return bedCost;
	}
	
	public Boolean[] getBought(){
		return bedBought;
	}
	
	public void setBought(int i, Boolean nBought){
			bedBought[i] = nBought;
	}
	
	
	
}
