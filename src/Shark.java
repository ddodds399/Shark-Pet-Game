import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.JOptionPane;

/**
 * 
 * @author Dean Dodds - 40103628
 *
 */

public class Shark extends Animal {
	
	private String colour, breed, gender, food;
	private String pic;
	
	//Arrays for constructor, these are the options the user can get when the shark pet is chosen
	private String[] aColour = {"Grey", "White", "Striped"};
	private String[] aBreed = {"Great White", "Sand", "Mako",  "Tiger", "Hammerhead"};
	private String[] aFood = {"Fish", "Penguin", "Coral", "Seal"};
	private String[] aGender = {"Male", "Female"};
	
	private int teeth, fins, weight, length, swimSkill, skillCounter, lifespan, coins;
	private double sharkAge;
	
	//These hold a true/false value, to decide whether the user can invoke certain methods
	private boolean huntStatus, scareStatus, swimStatus, coinMStatus, evolveStatus;
	
	//Shark Constructor - initialises animals name and difficulty setting
	//All the traits of the shark are randomised each time a new instance of shark is created
	//The arrays initialised above are used to hold options for the constructor to use
	/*@param name - name of shark - passed to super class
	 *@param difficulty - sets difficulty - passed to super class
	 */
	public Shark(String name, String set){
		super(name, set);
		//Randomly selects colour of shark
		int i = (int)(Math.random() * 3);
		colour = aColour[i];
		//Randomly selects breed of shark
		int j = (int)(Math.random() * 5);
		breed = aBreed[j];
		//Randomly selects favourite food of shark
		int k = (int)(Math.random() * 4);
		food = aFood[k];
		//Randomly selects gender of shark
		int l = (int)(Math.random() * 2);
		gender = aGender[l];
		//Randomly generates traits of shark
		teeth = 24 + (int)(Math.random() * 49);
		fins = 2 + (int)(Math.random() * 4);
		weight = 200 + (int)(Math.random() * 301);
		length = 1 + (int)(Math.random() * 4); 
		swimSkill = 0;
		skillCounter = 0;
		lifespan = 30 + (int)(Math.random() * 15);
		coins = 0;
		huntStatus = true;
		scareStatus = true;
		swimStatus = true;
		coinMStatus = false;
		evolveStatus = false;
	}
	
	//Shark Getter Methods
	public String getColour(){
		return colour;
	}
	
	public String getBreed(){
		return breed;
	}
	
	public String getFood(){
		return food;
	}
	
	public String getGender(){
	    return gender;
	}
	
	public int getTeeth(){
		return teeth;
	}
	
	public int getFins(){
		return fins;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getLength(){
		return length;
	}
	
	public int getSwimSkill(){
		return swimSkill;
	}
	
	public String getPic(){
		return pic;
	}
	
	public int getLifespan(){
		return lifespan;
	}
	
	public int getCoins(){
		return coins;
	}
	
	public boolean getEvolveStatus(){
		return evolveStatus;
	}
	
	public boolean getHuntStatus(){
		return huntStatus;
	}
	
	public boolean getScareStatus(){
		return scareStatus;
	}
	
	public boolean getSwimStatus(){
		return swimStatus;
	}
	
	//Shark Setter Methods
	public void setSwimSkill(int newSkill){
		swimSkill = newSkill;
	}
	
	public void setWeight(int newWeight){
		weight = newWeight;
	}
	
	public void setLength(int newLength){
		length = newLength;
	}
	
	public void setTeeth(int nTeeth){
		teeth = nTeeth;
	}
	
	public void setFins(int nFins){
		fins = nFins;
	}
	
	public void setCoins(int nCoins){
		coins = nCoins;
	}
	
	public void setLifespan(int nLife){
		lifespan = nLife;
	}
	
	public void setSwimStatus(boolean nSwimS){
		swimStatus = nSwimS;
	}
	
	public void setHuntStatus(boolean nHuntS){
		huntStatus = nHuntS;
	}
	
	public void setScareStatus(boolean nScareS){
		scareStatus = nScareS;
	}
	
	public void setEvolveStatus(boolean nEvoS){
		evolveStatus = nEvoS;
	}
	
	//This method checks the skillCounter variable, if it is equal to 5, the shark's swim skill levels up to a maximum of 5
	public void checkLevel(){
		if (swimSkill < 5)
		skillCounter++;
		if (skillCounter == 5 && swimSkill < 5){
			swimSkill++;
			JOptionPane.showMessageDialog(null, getPetName() + "'s Swimming Skill is now level " + getSwimSkill());
			skillCounter = 0;
		}	
	}
	
	//These method sets the shark out on a hunt
	//First, it checks that the shark hasn't been out recently.
	//If huntStatus is true then it can go hunting. This method will be set on the hunt button on the interface.

	public void goHunt(){
		if (huntStatus != true){
			JOptionPane.showMessageDialog(null,"\nYour Shark has been hunting recently. Please try again later.", null, JOptionPane.ERROR_MESSAGE);
		}
		else {
		JOptionPane.showMessageDialog(null, getPetName() + " is now out hunting. It will be back soon.");
		}
	}	
	
	//If huntStatus is true, then the shark goes out to hunt.
	//The next step is randomised, the shark can either return unsuccessful or come back with food.
	//If the catch is the same as it's favourite food, then it receives a major buff.
	
	public void finishHunt(){
		if (huntStatus == true) {
			huntStatus = false;
			int i = (int)(Math.random() * 2);
			if (i == 0){
				JOptionPane.showMessageDialog(null, getPetName() + "'s hunt was unsuccessful");
				decrEnergy(10);
				decrFun(15);
				decrHunger(15);
				incrBladder(5);
			}
			else {
					int j = (int)(Math.random() * 4);
				if (aFood[j] == food){
					JOptionPane.showMessageDialog(null, getPetName() + " has been out for a while and returned with " + aFood[j] + ".\nIt's your shark's favourite food, you receive a large status buff.");
					setEnergy(100);
					setHunger(100);
					setFun(100);
					incrBladder(5);
				} 
				else {
					JOptionPane.showMessageDialog(null, getPetName() + " has been out for a while and returned with " + aFood[j] + ".");
					incrEnergy(40);
					incrHunger(40);
					incrFun(40);
					incrBladder(5);
				}
				checkLevel();
				age();
			}
			scareStatus = true;
			swimStatus = true;
		}
	}
		
	
	//This method lets the shark out to swim.
	//This makes the social, fun, and therefore the happiness to increase, but while making energy and other statuses decrease.
	//Again like the hunt method this can only be used if swimStatus is true, meaning that the use can't overuse this.
	public void goSwim(){
		if (swimStatus == true){
			swimStatus = false;
			decrEnergy(4);
			incrSocial(15);
			incrFun(15);
			decHunger();
			decThirst();
			decHygiene();
			incrBladder(7);
			checkLevel();
			age();
			huntStatus = true;
			scareStatus = true;
		}
		else {
			JOptionPane.showMessageDialog(null, getPetName() + " can only swim so often. Please wait a moment.");
		}
	}
	
	//This method is seemingly useless until the shark is at its max swim level (5).
	//If use before swim level 5, the shark will take a status penalty.
	//Once the shark is at level 5, it will evolve making looking after it easier and change it's appearance. 
	public void goSplash(){
		checkLevel();
		if (swimSkill == 5 && evolveStatus == false){
			evolveStatus = true;
			JOptionPane.showMessageDialog(null, "What?! " + getPetName() + " is evolving!");
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File("audio/sharkEvolve.wav").getAbsoluteFile());
				Clip evolveClip = AudioSystem.getClip();
				evolveClip.open(audio);
				evolveClip.start();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Congratulations! You've trained your shark to it's highest level. It will be easier to look after from now on.");
			setDifficulty("Recruit");
			setHunger(100);
			setEnergy(100);
			setFun(100);
			setSocial(100);
			setHygiene(100);
			setThirst(100);
			setBladder(100);
			setHealth(100);
			age();
		}
		else if (evolveStatus == true){
			JOptionPane.showMessageDialog(null, getPetName() + " can only truly splash once in it's lifetime. That time has passed.");
		}
		
		else { 
			JOptionPane.showMessageDialog(null, getPetName() + " splashed. It achieved nothing.");
			decrEnergy(40);
			decrHunger(30);
			decrFun(35);
		}
	}
	
	//This method increase social, fun and happiness if the method is able to run.
	//Like the goHunt() and goSwim() method, the scareStatus has to be true for the goScare method to be successful.
	//This method will be set on the scare button on the interface.
	public void goScare(){
		if (scareStatus != true) {
			JOptionPane.showMessageDialog(null, "\nThe beach is still on alert. Try again later.");
		}
		else {
		scareStatus = false;	
		JOptionPane.showMessageDialog(null, "\nFinding a beach of tourists.");
		}
	}
	
	//goScare and finishScare have been separated to allow the interface to work as intended.
	//This will be run in a timer.
	public void finishScare(){
		JOptionPane.showMessageDialog(null, "\n" + getPetName() + " has successfully scared local tourists, but the beach is now on high alert.\nYou will have to wait before sending your shark out again.");
		incrFun(40);
		incrSocial(25);
		incrBladder(15);
		decEnergy();
		checkLevel();
		age();
		huntStatus = true;
		swimStatus = true;
	}
	
	//goRoll() is for only increasing the hygiene status of the shark.
	public void goRoll(){
		JOptionPane.showMessageDialog(null, getPetName() + " is thrashing about, all the dirt and grime is coming off.");	
		incrHygiene(15);
	}
	
	//This method is for increasing the traits of the shark when it ages.
	//After it has reached 5 years old it cannot grow anymore.
	public void grow(){
		if(getAge()<5){
		int i = 20 + (int)(Math.random() * 80);
		weight += i;
		int j = 1 + (int)(Math.random() * 4);
		length += j;
		int k = 2 + (int)(Math.random() * 9);
		teeth += k;
		JOptionPane.showMessageDialog(null, "Congratulations! " + getPetName() + " is now age " + (int)getAge() + ".\nIt has grow by " + j + "m, now weighs " + getWeight() + "kg and has " + k + " new teeth.");
		}
		else if (getAge() == 5){
			JOptionPane.showMessageDialog(null, getPetName() + " is aged 5 and fully mature. It cannot grow anymore.");
		}	
	}
	
	//This method is used at the end of most other methods that affect statuses.
	//The age slowly increases by 0.01. As the lifespan of the shark is between 30 and 45, this will give longevity to the game.
	//It also checks to see if the age of the shark is larger the lifespan, if so the shark dies.
	public void age(){
		
		sharkAge += 0.03;
		if (sharkAge > 1){
			incrAge(1);
			sharkAge = 0;
			grow();
		}
		
		if (getAge() > lifespan){
			die();
		}
	}
	
	//Thhis method is used to allow the user to randomly find coins as the game progresses.
	//These will be used in the shop to buy items.
	public void findCoins(){
		if (coins<9999){
		int i = (int)(Math.random() * 3);
			if (i == 1){
				int j = 1 + (int)(Math.random() * 10);
				coins += j;
				if (coins > 9999){
					coins = 9999;
				}
				if (coinMStatus == false) {
					JOptionPane.showMessageDialog(null, getPetName() + " has just found " +  j + " Cutlass Coin(s) while roaming!\nThis will continue to happen as the game progresses. Coins can be spent at the shop.");
					coinMStatus = true;
				}
			}
		}
	}
	
	//This roam method is added to a move timer on the interface that moves the shark around.
	//While it's moving the shark's stats decrease but it has the opportunity to find coins.
	public void roam(){
		decrEnergy(1);
		decHunger();
		decrThirst(2);
		decrHygiene(2);
		decBladder();
		findCoins();
	}
	
	//This method is used along with the goHunt() and finishHunt() methods. 
	//This determines the rate at which the states of the shark decrease while it's out hunting.
	public void hunt(){
		decrSocial(3);
		decrBladder(2);
		decHunger();
		incrFun(1);
		decrThirst(3);
		decrEnergy(4);
		decHygiene();
	}
	
	//Shark Override Methods
	
	@Override
	public void play(){
			incrEnergy(5);
			decSocial();
			incrFun(10);
			decHunger();
			decrThirst(2);
			decrHygiene(3);
			decBladder();
			age();
		
	}
	
	@Override
	public void feed(){
			decEnergy();
			decSocial();
			incrFun(5);
			incrHunger(10);
			decrThirst(2);
			decrHygiene(2);
			decBladder();
			age();
	}
	
	@Override
	public void sleep(){
			incrEnergy(10);
			decrSocial(2);
			decFun();
			decHunger();
			decThirst();
			decrHygiene(2);
			decrBladder(1);
			age();
	}
	
	@Override
	public void bathe(){
			incrHygiene(10);
			incrSocial(7);
			incrFun(1);
			decHunger();
			decThirst();
			decrBladder(3);
			age();
	}
	
	@Override
	public void water(){
			decHygiene();
			incrSocial(5);
			decFun();
			decHunger();
			incrThirst(10);
			decrBladder(3);
			age();		
	}
	
	@Override
	public void toilet(){
		incrHygiene(7);
		decrSocial(2);
		decFun();
		decHunger();
		decThirst();
		incrBladder(10);
		age();	
	}
	
	@Override
	public void idlePet(){
		
		decEnergy();
		decFun();
		decSocial();
		decHunger();
		decThirst();
		decHygiene();
		decBladder();
		age();
	}
	
}
	

