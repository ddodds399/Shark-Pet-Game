import javax.swing.ImageIcon;

/**
 * 
 * @author Dean Dodds - 40103628
 *
 */

//This interface will be implemeted in the SharkToys, SharkFood and SharkBedding classes.
public interface ShopItem {

	//The following methods will be used in each of the classes mentioned above.
	
	public ImageIcon[] getPic();

	public String[] getName();
	
	public String[] getDesc();

	public int[] getCost();
	
	public Boolean[] getBought();
	
	public void setBought(int i, Boolean nBought);
	
}
