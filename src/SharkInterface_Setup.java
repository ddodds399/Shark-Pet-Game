import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Dean Dodds - 40103628
 *
 */

//This class is used for setting up both the main and shop interface.
//A new instance of this is run from the Start_Game.java file.


public class SharkInterface_Setup {

	public static Shark shark;
	public static SharkInterface sharkGUI;
	public static SharkShopInterface shopGUI;
	public static SharkToys sharkToys;
	public static SharkFood sharkFood;
	public static SharkBedding sharkBed;
	public static String sharkName, emptyString = "", itemSelection = "Toys";
	public static String choosenDiff;
	public static String[] possibleDiff = {"Recruit", "Regular", "Hardened"};
	public static String[] pregameOptions = {"New Game", "Load Game"};
	public static String[] endgameOptions = {"New Game", "Load Game", "Quit"};
	public static Timer idleTimer, moveTimer, huntTimer, swimTimer, scareTimer, rollTimer;
	public static ImageIcon sharkStatic, sharkSpecial;
	public static int huntCounter = 0, scareCounter = 0, swimCounter = 0, rollCounter = 0, curPosition = 0, minPosition = 0, maxPosition = 4;
	public static JFileChooser fileChooser;
	public static File saveFile;
	public static FileReader filereader;
	public static PrintWriter saveGame;
	public static boolean saveStatus, pauseStatus, swimCheat;
	public static String[] loadData;
	public static BufferedReader loader;
	
	//The main method here is what is called from the group interface.
	//It displays to the user if they want to start a new game or load a previous save file.
	public static void main(String[] args) throws IOException {
		
		int i = JOptionPane.showOptionDialog(null, "Do you want to start with a new shark pet, or load a previously saved one?",
			    null, JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, pregameOptions, pregameOptions[1]);
		if (i == 0){
		newGame();
		}
		else if (i == 1) {
			loadedGame();
		}
		else {
			WelcomeScreen home = new WelcomeScreen();
			home.getWindow();
		}
	}
	
	//This method is called if the user chooses the new game option from the previous method.
	//This method passes sharkName to the shark constructor later on.
	//Validation is in place to make sure the user actually enters a name, and one that contains no symbols only letters.
	public static void newGame(){
		
		do {
			sharkName = JOptionPane.showInputDialog("What will your shark be called?  (No Spaces)");
			if (sharkName.equals(emptyString) || !sharkName.matches("[a-zA-Z]+"))
			{
				JOptionPane.showMessageDialog(null, "Please enter a name into the box that only contains letters of the alphabet.");
			}
		}
		
		while  (sharkName.equals(emptyString) || !sharkName.matches("[a-zA-Z]+"));
		
		chooseDifficulty();
		
		startGame();
	}
	
	//This method is called if the user chooses to load a saved game.
	//This will call the loadGame() method that will be explained later on to read in files from a text file.
	//This sets up the values of the shark's status from the text file along with setting up any parts of the GUI that need loaded.
	//The values taken from the save file are stored in an array and used to setup the values here. Most need parsed to ints.
	//This also sets the variable saveStatus to true meaning the user can overwrite the file they loaded without having to choose the file again.
	//Shop items are also loaded here so the user still has the items they've bought.
	public static void loadedGame() throws IOException {
		try {
			loadGame();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sharkName = loadData[0];
		choosenDiff = loadData[10];
		loadedDifficulty();
		startGame();
		shark.setAge(Double.parseDouble(loadData[1]));
		sharkGUI.getBreedLabel().setText("Breed: " + loadData[2] + " Shark");
		sharkGUI.getFoodLabel().setText("Favourite Food: " + loadData[3]);
		sharkGUI.getColourLabel().setText("Colour: " + loadData[4]);
		sharkGUI.getGenderLabel().setText("Gender: " + loadData[5]);
		shark.setWeight(Integer.parseInt(loadData[6]));
		shark.setLength(Integer.parseInt(loadData[7]));
		shark.setTeeth(Integer.parseInt(loadData[8]));
		shark.setFins(Integer.parseInt(loadData[9]));
		shark.setSwimSkill(Integer.parseInt(loadData[11]));
		shark.setCoins(Integer.parseInt(loadData[12]));
		shark.setHealth(Integer.parseInt(loadData[13]));
		shark.setHunger(Integer.parseInt(loadData[14]));
		shark.setEnergy(Integer.parseInt(loadData[15]));
		shark.setHygiene(Integer.parseInt(loadData[16]));
		shark.setSocial(Integer.parseInt(loadData[17]));
		shark.setBladder(Integer.parseInt(loadData[18]));
		shark.setFun(Integer.parseInt(loadData[19]));
		shark.setThirst(Integer.parseInt(loadData[20]));
		shark.setScareStatus(Boolean.parseBoolean(loadData[21]));
		shark.setHuntStatus(Boolean.parseBoolean(loadData[22]));
		shark.setSwimStatus(Boolean.parseBoolean(loadData[23]));
		shark.setEvolveStatus(Boolean.parseBoolean(loadData[24]));
		if (shark.getEvolveStatus() == true){
			sharkGUI.setSharkPic("image/Dean-Shark/sharkevo.png");
			sharkGUI.setSharkOverPic("image/Dean-Shark/sharkevoOver.png");
		}
		shark.setLifespan(Integer.parseInt(loadData[25]));
		for(int i = 0; i < 5; i++){
			sharkToys.setBought(i, Boolean.parseBoolean(loadData[26 + i]));
		}
		for(int j = 0; j < 5; j++){
			sharkFood.setBought(j, Boolean.parseBoolean(loadData[31 + j]));
		}
		for(int k = 0; k < 5; k++){
			sharkBed.setBought(k, Boolean.parseBoolean(loadData[36 + k]));
		}
		saveStatus = true;
	}

	//This method is called after the user has input a name for the new game.
	//The user will be presented with three options,  Recruit, Regular and Hardened.
	//The user will then be asked to confirm their choice.
	public static void chooseDifficulty(){
		
			int i = JOptionPane.showOptionDialog(null, "Please select your preferred difficulty.\nThis will determine how easy it is to look after your shark.",
			    null, JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, possibleDiff, possibleDiff[2]);
		
			choosenDiff = possibleDiff[i];
		
			int j = JOptionPane.showConfirmDialog(null,"You have selected " + choosenDiff + ". \nIs this your choosen difficulty?" , "Confirm" , JOptionPane.YES_NO_OPTION);
		
			if (j == 1){
				chooseDifficulty();
			}
	}
		
	//This method is called in the loadedGame() method.
	//It allows the user to change the difficulty of their loaded game.
	public static void loadedDifficulty(){
		
		if (choosenDiff.equals("Recruit")){
			choosenDiff = possibleDiff[0];
			int j = JOptionPane.showConfirmDialog(null,"You are playing on " + choosenDiff + ".\nDo you wish to change difficulty?" , null, JOptionPane.YES_NO_OPTION);
			if (j == 0){
			chooseDifficulty();
			}
		}
		else if (choosenDiff.equals("Regular")){
			choosenDiff = possibleDiff[1];
			int j = JOptionPane.showConfirmDialog(null,"You are playing on " + choosenDiff + ".\nDo you wish to change difficulty?" , null, JOptionPane.YES_NO_OPTION);
			if (j == 0){
			chooseDifficulty();
			}
		}
		else if (choosenDiff.equals("Hardened")){
			choosenDiff = possibleDiff[2];
			int j = JOptionPane.showConfirmDialog(null,"You are playing on " + choosenDiff + ".\nDo you wish to change difficulty?" , null, JOptionPane.YES_NO_OPTION);
			if (j == 0){
			chooseDifficulty();
			}
		}
		
	}
	
	//This method is called in both the new game and loaded game methods.
	//This creates new instances of the shark and shark interface.
	//It also sets up all buttons, bars and timers.
	//The shop and related objects have instances setup in this method.
	public static void startGame(){
		
		shark = new Shark(sharkName, choosenDiff);
		
		setupShark();
		
		sharkGUI = new SharkInterface();
		
		setupInterface();
		setupSave();
		setupQuit();
		setupFeed();
		setupSleep();
		setupPlay();
		setupInteract();
		setupBathe();
		setupDrink();
		setupToilet();
		setupHunt();
		setupSwim();
		setupScare();
		setupRoll();
		setupSplash();
		setupPause();

		pauseStatus = false;
		saveStatus = false;
		swimCheat = false;
		
		shopGUI = new SharkShopInterface();
		sharkToys = new SharkToys();
		sharkFood = new SharkFood();
		sharkBed = new SharkBedding();
		setupShopInterface();
		setupShop();
		
		idleTimer();
		moveTimer();
	}
	
	//This method is used above, it sets the name and difficulty for the shark, which affect the rates that the bars decrease by.
	//Also sets up the static image for the shark.
	public static void setupShark(){
		
		shark.setPetName(sharkName);
		shark.setDifficulty(choosenDiff);
		shark.setRates();
		sharkStatic = new ImageIcon("image/Dean-Shark/sharksmall.png");
	}
	
	//This method is used to display all aspects and traits of the shark on the interface.
	public static void setupInterface(){
		
		sharkGUI.getNameLabel().setText("Name: " + shark.getPetName());
		sharkGUI.getAgeLabel().setText("Age: " + (int)shark.getAge());
		sharkGUI.getLifespanLabel().setText("Lifespan: " + shark.getLifespan());
		sharkGUI.getBreedLabel().setText("Breed: " + shark.getBreed() + " Shark");
		sharkGUI.getFoodLabel().setText("Favourite Food: " + shark.getFood());
		sharkGUI.getColourLabel().setText("Colour: " + shark.getColour());
		sharkGUI.getGenderLabel().setText("Gender: " + shark.getGender());
		sharkGUI.getWeightLabel().setText("Weight: " + shark.getWeight() + "kg");
		sharkGUI.getLengthLabel().setText("Length: " + shark.getLength() + "m");
		sharkGUI.getTeethLabel().setText("Teeth: " + shark.getTeeth());
		sharkGUI.getFinsLabel().setText("Fins: " + shark.getFins());
		sharkGUI.getDiffLabel().setText("Difficulty: " + shark.getDifficulty());	
		sharkGUI.getSkillLabel().setText("Swim Skill Level: " + shark.getSwimSkill());
		sharkGUI.getHealthLabel().setText("Health: " + shark.getHealth());
		sharkGUI.getCoins().setText("Cutlass Coins: " + shark.getCoins());
		sharkGUI.getHungerBar().setValue(100);
		sharkGUI.getEnergyBar().setValue(100);
		sharkGUI.getSocialBar().setValue(100);
		sharkGUI.getBladderBar().setValue(100);
		sharkGUI.getHappyBar().setValue(100);
		sharkGUI.getThirstBar().setValue(100);
		sharkGUI.getHygieneBar().setValue(100);
		
	}
	
	//This method creates an idle timer that runs in the background affecting the statuses of the shark.
	//If the health of the shark reaches zero or it's age is larger than it's lifespan, the shark will die.
	//After the shark dies, the user is presented with an option to start a new game, load, or quit.
	//Certain effects from shop items are included in this method.
	public static void idleTimer(){
		
		idleTimer = new Timer();
		idleTimer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				
				if (sharkBed.getBought()[4] == false){
				shark.idlePet();
				shark.stateMinMax();
				} else {
					shark.setBladder(100);
					shark.setFun(100);
					shark.setHunger(100);
					shark.setEnergy(100);
					shark.setSocial(100);
					shark.setThirst(100);
					shark.setHygiene(100);
				}
				
				if (sharkFood.getBought()[4] == true){
					shark.setHunger(100);
					sharkGUI.getHungerBar().setValue(100);
				}
				
				if (sharkBed.getBought()[0] == true && shark.getEnergy() < 20){
					shark.setEnergy(20);
					sharkGUI.getEnergyBar().setValue(20);
				}
				
				if (sharkBed.getBought()[1] == true){
					if (shark.getEnergy() < 50){
						shark.setEnergy(50);
						sharkGUI.getEnergyBar().setValue(50);
					}
					if (shark.getFun() < 50){
						shark.setFun(50);
						sharkGUI.getHappyBar().setValue(50);
					}
				}
				
				if (sharkBed.getBought()[2] == true){
					if (shark.getEnergy() < 50){
						shark.setEnergy(50);
						sharkGUI.getEnergyBar().setValue(50);
					}
					if (shark.getFun() < 50){
						shark.setFun(50);
						sharkGUI.getHappyBar().setValue(50);
					}
					if (shark.getSocial() < 50){
						shark.setSocial(50);
						sharkGUI.getSocialBar().setValue(50);
					}
				}
				
				if (sharkBed.getBought()[3] == true){
					
					if(shark.getEnergy() < 75){
						shark.setEnergy(75);
						sharkGUI.getEnergyBar().setValue(75);
						sharkRepaint();
					}
					
					if(shark.getFun() < 75){
						shark.setFun(75);
						sharkGUI.getHappyBar().setValue(75);
						sharkRepaint();
					}
					
					if(shark.getSocial() < 75){
						shark.setSocial(75);
						sharkGUI.getSocialBar().setValue(75);
						sharkRepaint();
					}
				}
								
				sharkRepaint();
				
				if (shark.getHealth()<=0 || shark.getAlive() == false){
					moveTimer.cancel();
					idleTimer.cancel();
					shark.setHealth(0);
					sharkSpecial = new ImageIcon("image/Dean-Shark/sharkdead.png");
					sharkGUI.getPicLabel().setIcon(sharkSpecial);
					int i = JOptionPane.showOptionDialog(null, shark.getPetName() + " has died. Please select an option below.",
						    null, JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE, null, endgameOptions, endgameOptions[2]);
					if (i == 0) {
						sharkGUI.getSharkFrame().dispose();
							try {
								main(null);
							} catch (IOException e) {
								e.printStackTrace();
							}	
					}
					
					else if (i == 1) {
						sharkGUI.getSharkFrame().dispose();
						try {
							loadedGame();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					else if (i == 2) {
						System.exit(1);
					}
				}
			}
		}, 0, 750);
	}
	
	//This sets up a timer, like the idle timer, that runs in the background.
	//This moves the shark around the screen and affects the progressbars.
	//If the user has bought the treasure chest from the shop, they can find more coins within this method.
	public static void moveTimer(){
		
		moveTimer = new Timer();
		moveTimer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				setupRoam();
				if (sharkToys.getBought()[4] == true){
					shark.findCoins();
					shark.findCoins();
				}
				sharkRepaint();
				
			}
		}, 0, 1000);
	}
	
	//This method is used at the end of most status affecting method.
	//This repaints every element that can change on the interface.
	public static void sharkRepaint(){
		sharkGUI.getHungerBar().setValue(shark.getHunger());
		sharkGUI.getHungerBar().repaint();
		sharkGUI.getEnergyBar().setValue(shark.getEnergy());
		sharkGUI.getEnergyBar().repaint();
		sharkGUI.getSocialBar().setValue(shark.getSocial());
		sharkGUI.getSocialBar().repaint();
		sharkGUI.getBladderBar().setValue(shark.getBladder());
		sharkGUI.getBladderBar().repaint();
		sharkGUI.getHappyBar().setValue(shark.getFun());
		sharkGUI.getHappyBar().repaint();
		sharkGUI.getThirstBar().setValue(shark.getThirst());
		sharkGUI.getThirstBar().repaint();
		sharkGUI.getHygieneBar().setValue(shark.getHygiene());
		sharkGUI.getHygieneBar().repaint();
		sharkGUI.getHealthLabel().setText("Health: " + shark.getHealth());
		sharkGUI.getAgeLabel().setText("Age: " + (int)shark.getAge());
		sharkGUI.getLifespanLabel().setText("Lifespan: " + shark.getLifespan());
		sharkGUI.getSkillLabel().setText("Swim Skill Level: " + shark.getSwimSkill());
		sharkGUI.getWeightLabel().setText("Weight: " + shark.getWeight() + "kg");
		sharkGUI.getLengthLabel().setText("Length: " + shark.getLength() +"m");
		sharkGUI.getTeethLabel().setText("Teeth: " + shark.getTeeth());
		sharkGUI.getCoins().setText("Cutlass Coins: " + shark.getCoins());
		
	}
	
	//This method is used to disable all buttons on the interface.
	public static void disableButtons(){
		sharkGUI.getBatheButton().setEnabled(false);
		sharkGUI.getDrinkButton().setEnabled(false);
		sharkGUI.getFeedButton().setEnabled(false);
		sharkGUI.getHuntButton().setEnabled(false);
		sharkGUI.getInteractButton().setEnabled(false);
		sharkGUI.getPlayButton().setEnabled(false);
		sharkGUI.getRollButton().setEnabled(false);
		sharkGUI.getSaveButton().setEnabled(false);
		sharkGUI.getScareButton().setEnabled(false);
		sharkGUI.getShopButton().setEnabled(false);
		sharkGUI.getSleepButton().setEnabled(false);
		sharkGUI.getSplashButton().setEnabled(false);
		sharkGUI.getSwimButton().setEnabled(false);
		sharkGUI.getToiletButton().setEnabled(false);
	}
	
	//This method is used to enable all buttons on the interface.
	public static void enableButtons(){
		sharkGUI.getBatheButton().setEnabled(true);
		sharkGUI.getDrinkButton().setEnabled(true);
		sharkGUI.getFeedButton().setEnabled(true);
		sharkGUI.getHuntButton().setEnabled(true);
		sharkGUI.getInteractButton().setEnabled(true);
		sharkGUI.getPlayButton().setEnabled(true);
		sharkGUI.getRollButton().setEnabled(true);
		sharkGUI.getSaveButton().setEnabled(true);
		sharkGUI.getScareButton().setEnabled(true);
		sharkGUI.getShopButton().setEnabled(true);
		sharkGUI.getSleepButton().setEnabled(true);
		sharkGUI.getSplashButton().setEnabled(true);
		sharkGUI.getSwimButton().setEnabled(true);
		sharkGUI.getToiletButton().setEnabled(true);
	}
	
	//This method assigns an actionListener to the quit button.
	//It cancels all timers and asks the user if they want to quit.
	//If Yes, then they are returned to the main menu, if no, timers start again and they are returned to the interface.
	public static void setupQuit(){
		sharkGUI.getQuitButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				idleTimer.cancel();
				moveTimer.cancel();
				int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", null, JOptionPane.YES_NO_OPTION);
				if ( i == 0) {
					sharkGUI.getSharkFrame().dispose();
					WelcomeScreen home = new WelcomeScreen();
					home.getWindow();
				}
				else 
					idleTimer();
					moveTimer();
			}
		});
	}
	
	//This method assigns an actionListener to the feed button.
	//It affects the progressbars, mainly hunger.
	//If certain food items are bought from the shop, the hunger bar is affected differently.
	public static void setupFeed(){
		sharkGUI.getFeedButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sharkFood.getBought()[0] == true){
					shark.setHunger(100);
					sharkFood.setBought(0, false);
				} else if (sharkFood.getBought()[1] == true) {
					shark.setHunger(100);
					shark.setEnergy(100);
					sharkFood.setBought(1, false);
				} else if (sharkFood.getBought()[3] == true) {
					shark.setHunger(100);
					shark.setEnergy(100);
					shark.setFun(100);
					sharkFood.setBought(3, false);
				} else {
					shark.feed();
				}
				sharkRepaint();
			}
		});
	}
	
	//This method assigns an actionListener to the sleep button.
	//It affects the progressbars, mainly energy.
	public static void setupSleep(){
		sharkGUI.getSleepButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shark.sleep();
				sharkRepaint();
			}
		});
	}
	
	//This method assigns an actionListener to the play button.
	//It affects the progressbars, mainly fun.
	//If the beachball has been purchased from the shop, playing is more effective.
	public static void setupPlay(){
		sharkGUI.getPlayButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sharkToys.getBought()[0] == true){
					shark.incrFun(20);
					shark.incrEnergy(10);
					shark.decSocial();
					shark.decHunger();
				} else {
					shark.play();
				}
				sharkRepaint();
			}
		});
	}
	
	//This method assigns an actionListener to the interact button.
	//It affects the progressbars, mainly social.
	public static void setupInteract(){
		sharkGUI.getInteractButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shark.interact();
				sharkRepaint();
			}
		});
	}
	
	//This method assigns an actionListener to the bathe button.
	//It affects the progressbars, mainly hygiene.
	public static void setupBathe(){
		sharkGUI.getBatheButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shark.bathe();
				sharkRepaint();
			}
		});
	}
	
	//This method assigns an actionListener to the drink button.
	//It affects the progressbars, mainly thirst.
	//If the slushie food has been purchased, thirst is set to 100.
	public static void setupDrink(){
		sharkGUI.getDrinkButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sharkFood.getBought()[2]){
					shark.setThirst(100);
					sharkFood.setBought(2, false);
				} else {
					shark.water();
				}
				sharkRepaint();
			}
		});
	}
	
	//This method assigns an actionListener to the toilet button.
	//It affects the progressbars, mainly bladder.
	public static void setupToilet(){
		sharkGUI.getToiletButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shark.toilet();
				sharkRepaint();
			}
		});
	}
	
	//This method is used in the roamTimer.
	//This randomly generates a position for the shark to move to on the top panel.
	public static void setupRoam(){
				int x = (int)(Math.random() * 601);
				int y= (int)(Math.random() * 180);
				sharkGUI.getPicLabel().setBounds(x,y,191,200);
				shark.roam();
	}
	
	//This method assigns an actionListener to the hunt button.
	//When clicked it checks if it has been out hunting recently by checking the huntStatus variable.
	//If it is true, then the shark go's out and hunts, this makes the shark invisible and the bars decrease at a different rate due to the hunt method.
	//When the shark returns it will display to the user one of three options:
		//The hunt was unsuccessful - meaning its stats decrease.
		//Shark caught "  " - this makes its hunger and fun increase.
		//Shark caught its favourite food - if it catches its favourite food, which is generated when the instance is created, it will receive a large stat boost.
	//If huntStatus is false it will display a prompt, telling the user they can't do that yet.
	//If the toy boat has been purchased from the shop, hunt doesn't idley affect towers.
	public static void setupHunt(){
		sharkGUI.getHuntButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				idleTimer.cancel();
				moveTimer.cancel();
				disableButtons();
				sharkGUI.getPicLabel().setVisible(false);
				shark.goHunt();
				huntTimer = new Timer();
				huntTimer.scheduleAtFixedRate(new TimerTask(){
					public void run(){
						if (sharkToys.getBought()[3] == false){
							shark.hunt();
							shark.stateMinMax();
							sharkRepaint();
						}
						shark.findCoins();
						huntCounter++;
						if (huntCounter == 5){
							huntTimer.cancel();
							shark.finishHunt();
							sharkGUI.getPicLabel().setVisible(true);
							sharkRepaint();
							enableButtons();
							sharkGUI.getHuntButton().setEnabled(false);
							idleTimer();
							moveTimer();
							huntCounter = 0;
						}
					}
				}, 0, 1000);			
			}
		});
	}
	
	//This method assigns an actionListener to the swim button.
	//When clicked it checks if it has been out swimming recently by checking the swimStatus variable.
	//If it is true, then the shark swims increasing happiness.
	//If swimStatus is false it will display a prompt, telling the user they can't do that yet.
	//If the rubber ring is purchased from the shop, then the user gets one free level for swimming.
	public static void setupSwim(){
		sharkGUI.getSwimButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sharkToys.getBought()[1] == true){
					if (swimCheat == false){
						shark.setSwimSkill(shark.getSwimSkill() + 1);
						sharkGUI.getSkillLabel().setText("Swim Skill Level: " + shark.getSwimSkill());
						swimCheat = true;
					}
				}	
				sharkSpecial = new ImageIcon("image/Dean-Shark/sharkswim.png");
				if (shark.getEvolveStatus() == true){
				sharkSpecial = new ImageIcon("image/Dean-Shark/sharkevoSwim.png");
				}
				sharkGUI.getPicLabel().setIcon(sharkSpecial);
				disableButtons();
				shark.goSwim();
				swimTimer = new Timer();
				swimTimer.scheduleAtFixedRate(new TimerTask(){
					public void run(){
						shark.stateMinMax();
						sharkRepaint();
						swimCounter++;
						if (swimCounter == 5){
							swimTimer.cancel();
							sharkGUI.getPicLabel().setIcon(sharkStatic);
							swimCounter = 0;
						}
					}
				}, 0, 1000);
				enableButtons();
				sharkGUI.getSwimButton().setEnabled(false);
			}
			});
	}
	
	//This method assigns an actionListener to the scare button.
	//When clicked it checks if it has been out scaring recently by checking the scareStatus variable.
	//If it is true, then the shark go's out and scares, this makes the shark invisible and the bars decrease at a different rate due to the roam method.
	//When the shark returns it will display a message telling the user that it can't go out for a while.
	//If scareStatus is false it will display a prompt, telling the user they can't do that yet.
	//If the pirate hat is purachased from the shop then scaring doesn't idley affect states.
	public static void setupScare(){
		sharkGUI.getScareButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				idleTimer.cancel();
				moveTimer.cancel();
				sharkGUI.getPicLabel().setVisible(false);
				disableButtons();
				shark.goScare();
				shark.findCoins();
				scareTimer = new Timer();
				scareTimer.scheduleAtFixedRate(new TimerTask(){
					public void run(){
						if (sharkToys.getBought()[2] == false){
							shark.idlePet();
							shark.stateMinMax();
							sharkRepaint();
						}
						scareCounter++;
						if (scareCounter == 5){
							scareTimer.cancel();
							shark.finishScare();
							sharkGUI.getPicLabel().setVisible(true);
							
							if (sharkBed.getBought()[3] == true){
								shark.setBladder(100);
								shark.setFun(100);
								shark.setHunger(100);
								shark.setEnergy(100);
								shark.setSocial(100);
								shark.setThirst(100);
								shark.setHygiene(100);
							}
							
							sharkRepaint();
							enableButtons();
							sharkGUI.getScareButton().setEnabled(false);
							idleTimer();
							moveTimer();
							scareCounter = 0;
						}
					}
				}, 0, 1000);				
			}
			});
	}
	
	//This method assigns an actionListener to the roll button.
	//Rolling is for only increasing the hygiene status of the shark, it does not affect any other state.
	public static void setupRoll(){
		sharkGUI.getRollButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sharkSpecial = new ImageIcon("image/Dean-Shark/sharkroll.png");
				sharkGUI.getPicLabel().setIcon(sharkSpecial);
				disableButtons();
				shark.goRoll();
				rollTimer = new Timer();
				rollTimer.scheduleAtFixedRate(new TimerTask(){
					public void run(){
						shark.stateMinMax();
						sharkRepaint();
						rollCounter++;
						if (rollCounter == 5){
							rollTimer.cancel();
							sharkGUI.getPicLabel().setIcon(sharkStatic);
							rollCounter = 0;
						}
					}
				}, 0, 1000);
				enableButtons();
			}
			});
	}
	
	//This method assigns an actionListener to the spalsh button.
	//If the shark's swim level is less than 5, the shark will take a status penalty.
	//If the shark is at level 5, it will evolve changing the shark icon.
	public static void setupSplash(){
		sharkGUI.getSplashButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shark.goSplash();
				if (shark.getEvolveStatus() == true){
					sharkStatic = new ImageIcon("image/Dean-Shark/sharkevo.png");
					sharkGUI.setSharkPic("image/Dean-Shark/sharkevo.png");
					sharkGUI.setSharkOverPic("image/Dean-Shark/sharkevoOver.png");
					sharkGUI.getPicLabel().setIcon(sharkStatic);
				}
				sharkRepaint();
			}
			});
	}
	
	//This method assigns an actionListener to the save button.
	//This will call the saveGame() method.
	public static void setupSave(){
		sharkGUI.getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveGame();
			}
			});
	}
	
	//This method assigns an actionListener to the pause button.
	//This will allow the user to pause the game indefinitely until they want to resume.
	public static void setupPause(){
		sharkGUI.getPauseButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			if (pauseStatus == false){
					idleTimer.cancel();
					moveTimer.cancel();
					disableButtons();
					sharkGUI.getPauseButton().setEnabled(true);
					pauseStatus = true;
				}
			else if (pauseStatus == true){
					idleTimer();
					moveTimer();
					enableButtons();
					pauseStatus = false;
				}
			}
			});
	}
	
	//This method assigns an actionListener to the shop button.
	//This will show the shark shop and stop timers.
	public static void setupShop(){	
		sharkGUI.getShopButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				shopGUI.getShopFrame().setVisible(true);
				idleTimer.cancel();
				moveTimer.cancel();
				sharkGUI.getSharkFrame().setEnabled(false);
				shopGUI.getBalanceLabel().setText("Coins: " + shark.getCoins());
			}
			});
	}
	
	//This method will display a filechooser first of all.
	//This will allow the user to select where they want to save the file and what it should be called. It automatically saves as a ".txt" file.
	/**
	 * 	Syntax for save files
	 * 		- name
	 * 		- age 
	 * 		- breed
	 * 		- favourite food
	 * 		- colour
	 * 		- gender
	 * 		- weight
	 * 		- length
	 * 		- teeth
	 * 		- fins
	 * 		- difficulty
	 * 		- swim skill
	 * 		- coins
	 * 		- health
	 * 		- hunger
	 * 		- energy
	 * 		- hygiene
	 * 		- social
	 * 		- bladder
	 * 		- happiness
	 * 		- thirst
	 * 		- scareStatus
	 * 		- huntStatus
	 * 		- swimStatus
	 * 		- evolveStatus
	 * 		- lifespan
	 * 		-sharkToys bought
	 * 		-sharkFood bought
	 * 		-sharkBedding bought
	 * @throws FileNotFoundException 
	 * 	
	 */
	public static void saveGame() {
		idleTimer.cancel();
		moveTimer.cancel();
		fileChooser = new JFileChooser(new File("data/Sharksaves"));
		try {
			if (saveStatus == false){
				int choosenVal = fileChooser.showSaveDialog(fileChooser); 
				if (choosenVal == JFileChooser.APPROVE_OPTION) { 
					saveFile = new File(fileChooser.getSelectedFile() + ".txt");	
					JOptionPane.showMessageDialog(null, "Your game has saved been saved at " + saveFile +".\nThe next time you save with this shark, your previous savefile will be overwritten.");
				}       
			}	
			if (saveFile == null){
				idleTimer();
				moveTimer();
			}
			
			if (saveFile != null){
				saveStatus = true;
			}
			saveGame = new PrintWriter(saveFile);
			saveGame.println(shark.getPetName());
			saveGame.println(shark.getAge());
			saveGame.println(shark.getBreed());
			saveGame.println(shark.getFood());
			saveGame.println(shark.getColour());
			saveGame.println(shark.getGender());
			saveGame.println(shark.getWeight());
			saveGame.println(shark.getLength());
			saveGame.println(shark.getTeeth());
			saveGame.println(shark.getFins());
			saveGame.println(shark.getDifficulty());
			saveGame.println(shark.getSwimSkill());
			saveGame.println(shark.getCoins());
			saveGame.println(shark.getHealth());
			saveGame.println(shark.getHunger());
			saveGame.println(shark.getEnergy());
			saveGame.println(shark.getHygiene());
			saveGame.println(shark.getSocial());
			saveGame.println(shark.getBladder());
			saveGame.println(shark.getFun());
			saveGame.println(shark.getThirst());
			saveGame.println(shark.getScareStatus());
			saveGame.println(shark.getHuntStatus());
			saveGame.println(shark.getSwimStatus());
			saveGame.println(shark.getEvolveStatus());
			saveGame.println(shark.getLifespan());
			for (int i = 0; i < 5; i++){
				saveGame.println(sharkToys.getBought()[i]);
			}
			for (int j = 0; j < 5; j++){
				saveGame.println(sharkFood.getBought()[j]);
			}
			for (int k = 0; k < 5; k++){
				saveGame.println(sharkBed.getBought()[k]);
			}
			idleTimer();
			moveTimer();
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (saveGame != null){
				saveGame.close();
			}
		}
	}	
	
	//This method is used at the beginning where the user is asked to select a new or old game.
	//If the user chooses to load a game they are presented with a file chooser.
	//A buffered reader is used read the text file and pass the values into an array.
	public static String[] loadGame() throws IOException {
		fileChooser = new JFileChooser(new File("data/Sharksaves"));
		fileChooser.setDialogTitle("Please select a savegame file.");
		try {
			int choosenVal = fileChooser.showOpenDialog(fileChooser); 
				if (choosenVal == JFileChooser.APPROVE_OPTION) { 
					saveFile = fileChooser.getSelectedFile();
				}
			loader = new BufferedReader(new FileReader(saveFile));
	        readLoadData(loader);
	        return loadData;
	    } finally {
	    	if (loader != null){
	         loader.close();
	    	} 
	    }	
	}
	
	//This method is used to set up a string array for the lines of text to be added to.
	//It also uses a for loop to run through each line of the text file.
	public static void readLoadData(BufferedReader fileIn) throws IOException  {
	      loadData = new String[42];
	      for (int i = 0; i < 41; i++)
	      {
	         readValue(fileIn, i);
	      }
	      fileIn.close();
	      
	   }
	
	//This method is the one in which the lines of text are read in and stored in the array.
	 public static void readValue(BufferedReader in, int i) throws IOException { 
	      loadData[i] = in.readLine();  
	      if (i == 40){
	    	  in.close();
	      }
	   }
	 
	 
	 
	 
	 //This is used for adding functionality to the shop.
	 //All buttons have their action listeners added here.
	 public static void setupShopInterface(){
		 
		 shopGUI.getShopFrame().setVisible(false);
		 shopGUI.getShopFrame().addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					shopGUI.getShopFrame().setVisible(false);
					idleTimer();
					moveTimer();
					sharkGUI.getSharkFrame().setEnabled(true);
				}
			});
		 
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
	 
	 //This method assigns an action listener to the shop's leave button.
	 //This will hide the shop interface and re-enable the main interface along with restarting timers.
	 public static void setupLeave(){
		 
		 shopGUI.getLeaveButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					sharkGUI.getSharkFrame().setEnabled(true);
					sharkGUI.getSharkFrame().setEnabled(true);
					shopGUI.getShopFrame().setVisible(false);
					idleTimer();
					moveTimer();
					sharkGUI.getSharkFrame().setEnabled(true);
				
				}
				}); 
	 }
	 
	//This method assigns an action listener to the shop's checkout button.
	//When the button is clicked it first checks what type of item is selected, toys, food or bedding.
	//It then checks whether the user has enough coins and if the selected it has been purchased or not.
	//If it is possible for the user to purchase the item, then they get confirmation that they got it.
	//In the case of bedding the user can only buy one home.
	 public static void setupCheckout(){
		 shopGUI.getCheckoutButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					if (itemSelection.equals("Toys")){
						if (sharkToys.getCost()[curPosition] > shark.getCoins()){
							JOptionPane.showMessageDialog(null,"You do not have enough Cutlass Coins to purchase this.");
						}
						
						else if (sharkToys.getBought()[curPosition] == true){
							JOptionPane.showMessageDialog(null,"You have already purchased this.");
						}
							
						else {
							int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to purchase the "  + sharkToys.getName()[curPosition] + " for " + sharkToys.getCost()[curPosition] + " coins?", "Checkout", JOptionPane.YES_NO_OPTION);
							if ( i == 0) {
								JOptionPane.showMessageDialog(null,"Congratulations! You have purchased the " + sharkToys.getName()[curPosition] +".");
								sharkToys.setBought(curPosition, true);
								shark.setCoins(shark.getCoins() - sharkToys.getCost()[curPosition]);
							}	
						}		
					}
					
					if (itemSelection.equals("Food")){
						if (sharkFood.getCost()[curPosition] > shark.getCoins()){
							JOptionPane.showMessageDialog(null,"You do not have enough Cutlass Coins to purchase this.");
						}
						
						else if (sharkFood.getBought()[curPosition] == true){
							JOptionPane.showMessageDialog(null,"You have already purchased this.");
						}
						
						else {
							int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to purchase the "  + sharkFood.getName()[curPosition] + " for " + sharkFood.getCost()[curPosition] + " coins?", "Checkout", JOptionPane.YES_NO_OPTION);
							if ( i == 0) {
								JOptionPane.showMessageDialog(null,"Congratulations! You have purchased the " + sharkFood.getName()[curPosition] +".");
								sharkFood.setBought(curPosition, true);
								shark.setCoins(shark.getCoins() - sharkFood.getCost()[curPosition]);
							}	
						}
					}
					
					if (itemSelection.equals("Bed")){
						if (sharkBed.getCost()[curPosition] > shark.getCoins()){
							JOptionPane.showMessageDialog(null,"You do not have enough Cutlass Coins to purchase this.");
						}
						
						else if (sharkBed.getBought()[curPosition] == true){
							JOptionPane.showMessageDialog(null,"You have already purchased this.");
						}
						
						else {
							int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to purchase the "  + sharkBed.getName()[curPosition] + " for " + sharkBed.getCost()[curPosition] + " coins?", "Checkout", JOptionPane.YES_NO_OPTION);
							if ( i == 0) {
								JOptionPane.showMessageDialog(null,"Congratulations! You have purchased the " + sharkBed.getName()[curPosition] +".");
								sharkBed.setBought(0, false);
								sharkBed.setBought(1, false);
								sharkBed.setBought(2, false);
								sharkBed.setBought(3, false);
								sharkBed.setBought(4, false);
								sharkBed.setBought(curPosition, true);
								shark.setCoins(shark.getCoins() - sharkBed.getCost()[curPosition]);
							}	
						}
					}
					
					shopGUI.getBalanceLabel().setText("Coins: " + shark.getCoins());
					
				}
				}); 
	 }
	 
	//This method assigns an action listener to the shop's next button.
	//This shows the next item in the chosen array, toys, food, or bedding. 
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
	 
	//This method assigns an action listener to the shop's previous button.
	//This shows the previous item in the chosen array, toys, food, or bedding. 
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
	 
	//This method assigns an action listener to the shop's toys button.
	//This sets the items the user can browse through to the toys.
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
	 
	//This method assigns an action listener to the shop's food button.
	//This sets the items the user can browse through to food.
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
	 
	//This method assigns an action listener to the shop's bedding button.
	//This sets the items the user can browse through to the bedding.
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
	 
	 //This method sets the item image, item name and item description labels on the interface based on what item is selected in the toy category.
	 public static void toyItems(){
			
			switch(curPosition){
			case 0 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[0]); shopGUI.getDescLabel().setText(sharkToys.getName()[0]); shopGUI.getDescArea().setText(sharkToys.getDesc()[0]); break;
			case 1 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[1]); shopGUI.getDescLabel().setText(sharkToys.getName()[1]); shopGUI.getDescArea().setText(sharkToys.getDesc()[1]); break;
			case 2 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[2]); shopGUI.getDescLabel().setText(sharkToys.getName()[2]); shopGUI.getDescArea().setText(sharkToys.getDesc()[2]); break;
			case 3 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[3]); shopGUI.getDescLabel().setText(sharkToys.getName()[3]); shopGUI.getDescArea().setText(sharkToys.getDesc()[3]); break;
			case 4 : shopGUI.getItemLabel().setIcon(sharkToys.getPic()[4]); shopGUI.getDescLabel().setText(sharkToys.getName()[4]); shopGUI.getDescArea().setText(sharkToys.getDesc()[4]); break;
			}		
		}
	 
	//This method sets the item image, item name and item description labels on the interface based on what item is selected in the food category.
	 public static void foodItems(){
		 
		 switch(curPosition){
			case 0 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[0]); shopGUI.getDescLabel().setText(sharkFood.getName()[0]); shopGUI.getDescArea().setText(sharkFood.getDesc()[0]); break;
			case 1 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[1]); shopGUI.getDescLabel().setText(sharkFood.getName()[1]); shopGUI.getDescArea().setText(sharkFood.getDesc()[1]); break;
			case 2 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[2]); shopGUI.getDescLabel().setText(sharkFood.getName()[2]); shopGUI.getDescArea().setText(sharkFood.getDesc()[2]); break;
			case 3 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[3]); shopGUI.getDescLabel().setText(sharkFood.getName()[3]); shopGUI.getDescArea().setText(sharkFood.getDesc()[3]); break;
			case 4 : shopGUI.getItemLabel().setIcon(sharkFood.getPic()[4]); shopGUI.getDescLabel().setText(sharkFood.getName()[4]); shopGUI.getDescArea().setText(sharkFood.getDesc()[4]); break;
			}
	 }
	 
	//This method sets the item image, item name and item description labels on the interface based on what item is selected in the bedding category.
	 public static void bedItems(){
		 
		 switch(curPosition){
			case 0 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[0]); shopGUI.getDescLabel().setText(sharkBed.getName()[0]); shopGUI.getDescArea().setText(sharkBed.getDesc()[0]); break;
			case 1 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[1]); shopGUI.getDescLabel().setText(sharkBed.getName()[1]); shopGUI.getDescArea().setText(sharkBed.getDesc()[1]); break;
			case 2 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[2]); shopGUI.getDescLabel().setText(sharkBed.getName()[2]); shopGUI.getDescArea().setText(sharkBed.getDesc()[2]); break;
			case 3 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[3]); shopGUI.getDescLabel().setText(sharkBed.getName()[3]); shopGUI.getDescArea().setText(sharkBed.getDesc()[3]); break;
			case 4 : shopGUI.getItemLabel().setIcon(sharkBed.getPic()[4]); shopGUI.getDescLabel().setText(sharkBed.getName()[4]); shopGUI.getDescArea().setText(sharkBed.getDesc()[4]); break;			
			}
	 }
	 
	 //This method increments the position in the arrays of toys, food, and bedding.
	 //This is used on the next button.
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
		
	//This method decrements the position in the arrays of toys, food, and bedding.
	//This is used on the previous button.
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
