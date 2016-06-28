import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * 
 * @author Dean Dodds - 40103628
 *
 */

public class SharkInterface {

	//All of the objects below are used to layout the shark interface.
	private static JFrame sharkFrame;
	private static JPanel allPanels, topPanel, botPanel, iconPanel, specialPanel, traitPanel;
	public static JButton saveButton, feedButton, sleepButton, huntButton, swimButton, playButton, rollButton, scareButton, splashButton, quitButton, shopButton, batheButton, interactButton, toiletButton, drinkButton, pauseButton;
	public static JLabel nameLabel, ageLabel,  coinLabel, coinCountLabel, hungerLabel, energyLabel, happyLabel, hygieneLabel, socialLabel, bladderLabel, thirstLabel, picLabel, healthLabel, breedLabel, teethLabel, finsLabel, foodLabel, skillLabel, weightLabel, lengthLabel, colourLabel, genderLabel, lifespanLabel, diffLabel, backgroundLabel, traitsLabel;
	public static ImageIcon pic, picOver, hungerPic, energyPic, happyPic, hygienePic, socialPic, bladderPic, thirstPic, backgroundPic, coinPic, pausePic;
	public static JProgressBar hungerBar, energyBar, happyBar, hygieneBar, socialBar, bladderBar, thirstBar;
	
	//SharkInterface Constructor - This creates a JFrame for the main panels to be added to. (allPanels, topPanel and botPanel).
	//It also adds all elements to the frame and panels.
	//sharkFrame is setup first and is given its parameters, then the JPanels are initialised and setup using the methods, addPanels, addBotComp and addTopComp.
	//Finally sharkFrame is set visible.
	public SharkInterface() {
		
		sharkFrame = new JFrame();
		sharkFrame.setTitle("QUBagotchi: Shark");
		sharkFrame.setSize(800,590);
		sharkFrame.setLocationRelativeTo(null);
		sharkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		sharkFrame.setResizable(false);
			
		allPanels = new JPanel();
		topPanel = new JPanel();
		botPanel = new JPanel();
		
		addPanels();
		addBotComp();
		addTopComp();
		sharkFrame.setVisible(true);
	}
	
	//This method adds the main panels to the JFrame and is used in the constructor.
	//The parameters of each panel is setup here.
	public static void addPanels(){
	
		allPanels.setLayout(new BoxLayout(allPanels, BoxLayout.Y_AXIS));
		allPanels.setMaximumSize(new Dimension(800,600));
		
		topPanel.setBackground(Color.BLUE);
		topPanel.setPreferredSize(new Dimension(800,380));
		topPanel.setLayout(null);
		allPanels.add(topPanel);
		
		botPanel.setBackground(Color.darkGray);
		botPanel.setPreferredSize(new Dimension(800,220));
		botPanel.setLayout(null);
		allPanels.add(botPanel);
		
		sharkFrame.add(allPanels);
		
	}
	
	//This method adds components to the topPanel and is used in the constructor.
	//The topPanel holds the shark image, background, shop button, coin counter and health counter.
	public static void addTopComp() {
		
		backgroundPic = new ImageIcon("image/Dean-Shark/sharkbackground.png");
		backgroundLabel = new JLabel(backgroundPic);
		backgroundLabel.setBounds(0,0,800,385);
		topPanel.add(backgroundLabel);
		
		pic = new ImageIcon("image/Dean-Shark/sharksmall.png");
		picLabel = new JLabel(pic);
		picLabel.setBounds(280,50,191,200);
		picOver = new ImageIcon("image/Dean-Shark/sharkover.png");
		backgroundLabel.add(picLabel);
		picLabel.addMouseListener(new MouseAdapter(){
			
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                picLabel.setIcon(picOver);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                picLabel.setIcon(pic);
            }
		});
			
		coinPic = new ImageIcon("image/Dean-Shark/sharkcoin.png");
		coinLabel = new JLabel(coinPic);
		coinLabel.setBounds(5,5,30,30);
		backgroundLabel.add(coinLabel);
		
		coinCountLabel = new JLabel("Cutlass Coins:");
		coinCountLabel.setForeground(Color.white);
		coinCountLabel.setBounds(37, 15 , 120, 10);
		backgroundLabel.add(coinCountLabel);
		
		healthLabel = new JLabel("Health:");
		healthLabel.setBounds(5,315,65,50);
		healthLabel.setForeground(Color.white);
		backgroundLabel.add(healthLabel);
		
		shopButton = new JButton("Shop");
		shopButton.setBounds(720,320,70,30);
		backgroundLabel.add(shopButton);
	}
	
	//This method adds components to the botPanel and is used in the constructor.
	//The botPanel holds 3 other panels that each hold important elements.
	public static void addBotComp() {
		
		addBotIcons();
		addBotBars();
		addBotUseButtons();
		addBotSpecButtons();
		
		JPanel spacer = new JPanel();
		spacer.setBounds(580,5,3,195);
		spacer.setBackground(Color.white);
		botPanel.add(spacer);
		
		addTraits();
		
	}
	
	//This method adds components to the iconPanel which is then added to the botPanel.
	//The iconPanel uses GridBagLayout and is used to display mainly the progressbars and status buttons.
	//This method adds the icons showing what each bar represents.
	public static void addBotIcons(){
		
		iconPanel = new JPanel();
		iconPanel.setMaximumSize(new Dimension(345,205));
		iconPanel.setLocation(350, 0);
		iconPanel.setBounds(0,0,345,205);
		iconPanel.setBackground(Color.darkGray);
		iconPanel.setLayout(new GridBagLayout());
		botPanel.add(iconPanel);
		
		GridBagConstraints b = new GridBagConstraints();
		
		hungerPic = new ImageIcon("image/Dean-Shark/sharkhunger.png");
		hungerLabel = new JLabel(hungerPic);
		b.weightx = 0.5;
		b.anchor = GridBagConstraints.FIRST_LINE_START;
		b.gridx = 0;
		b.gridy = 0;
		hungerLabel.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
				hungerLabel.setForeground(Color.WHITE);
                hungerLabel.setText("Hunger:");
                hungerLabel.setIcon(null); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	hungerLabel.setIcon(hungerPic); 
            	hungerLabel.setText(null);
            	try {
                    Thread.sleep(150);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt(); 
                }
            }
		});
		iconPanel.add(hungerLabel, b);
		
		energyPic = new ImageIcon("image/Dean-Shark/sharkenergy.png");
		energyLabel = new JLabel(energyPic);
		b.weightx = 0;
		b.gridx = 0;
		b.gridy = GridBagConstraints.RELATIVE;
		energyLabel.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	energyLabel.setForeground(Color.WHITE);
            	energyLabel.setText("Energy:");
            	energyLabel.setIcon(null); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	energyLabel.setIcon(energyPic); 
            	energyLabel.setText(null);
            	try {
                    Thread.sleep(150);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt(); 
                }
            }
		});
		iconPanel.add(energyLabel, b);
		
		hygienePic = new ImageIcon("image/Dean-Shark/sharkhygiene.png");
		hygieneLabel = new JLabel(hygienePic);
		b.weightx = 0;
		b.gridx = 0;
		b.gridy = GridBagConstraints.RELATIVE;
		hygieneLabel.addMouseListener(new MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt) {
            	hygieneLabel.setForeground(Color.WHITE);
            	hygieneLabel.setText("Hygiene:");
            	hygieneLabel.setIcon(null); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	hygieneLabel.setIcon(hygienePic); 
            	hygieneLabel.setText(null);
            	try {
                    Thread.sleep(150);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt(); 
                }
            }
		});
		iconPanel.add(hygieneLabel, b);

		socialPic = new ImageIcon("image/Dean-Shark/sharksocial.png");
		socialLabel = new JLabel(socialPic);
		b.weightx = 1;
		b.gridx = 0;
		b.gridy = GridBagConstraints.RELATIVE;
		socialLabel.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	socialLabel.setForeground(Color.WHITE);
            	socialLabel.setText("Social:");
            	socialLabel.setIcon(null); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	socialLabel.setIcon(socialPic); 
            	socialLabel.setText(null);
            	try {
                    Thread.sleep(150);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt(); 
                }
            }
		});
		iconPanel.add(socialLabel, b);

		bladderPic = new ImageIcon("image/Dean-Shark/sharkbladder.png");
		bladderLabel = new JLabel(bladderPic);
		b.weightx = 0;
		b.gridx = 0;
		b.gridy = GridBagConstraints.RELATIVE;
		bladderLabel.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	bladderLabel.setForeground(Color.WHITE);
            	bladderLabel.setText("Bladder:");
            	bladderLabel.setIcon(null); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	bladderLabel.setIcon(bladderPic); 
            	bladderLabel.setText(null);
            	try {
                    Thread.sleep(150);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt(); 
                }
            }
		});
		iconPanel.add(bladderLabel, b);
		
		happyPic = new ImageIcon("image/Dean-Shark/sharkhappy.png");
		happyLabel = new JLabel(happyPic);
		b.weightx = 0;
		b.gridx = 0;
		b.gridy = GridBagConstraints.RELATIVE;
		happyLabel.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	happyLabel.setForeground(Color.WHITE);
            	happyLabel.setText("Happiness:");
            	happyLabel.setIcon(null); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	happyLabel.setIcon(happyPic); 
            	happyLabel.setText(null);
            	try {
                    Thread.sleep(150);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt(); 
                }
            }
		});
		iconPanel.add(happyLabel, b);
		
		thirstPic = new ImageIcon("image/Dean-Shark/sharkthirst.png");
		thirstLabel = new JLabel(thirstPic);
		b.weightx = 0;
		b.gridx = 0;
		b.gridy = GridBagConstraints.RELATIVE;
		thirstLabel.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	thirstLabel.setForeground(Color.WHITE);
            	thirstLabel.setText("Thirst:");
            	thirstLabel.setIcon(null); 
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            	thirstLabel.setIcon(thirstPic); 
            	thirstLabel.setText(null);
            	try {
                    Thread.sleep(150);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt(); 
                }
            }
		});
		iconPanel.add(thirstLabel, b);
		
	}
	
	//This method adds the progressbars to the iconPanel. 
	//These show the user the values for each of the shark's states.
	public static void addBotBars(){
		
		GridBagConstraints c = new GridBagConstraints();
		
		hungerBar = new JProgressBar(0,100);
		hungerBar.setPreferredSize(new Dimension(200,28));
		c.gridx = GridBagConstraints.RELATIVE;
		c.gridy = 0;
		iconPanel.add(hungerBar);
		
		energyBar = new JProgressBar(0,100);
		energyBar.setPreferredSize(new Dimension(200,28));
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(energyBar, c);
		
		hygieneBar = new JProgressBar(0,100);
		hygieneBar.setPreferredSize(new Dimension(200,28));
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(hygieneBar, c);
		
		socialBar = new JProgressBar(0,100);
		socialBar.setPreferredSize(new Dimension(200,28));
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(socialBar, c);
		
		bladderBar = new JProgressBar(0,100);
		bladderBar.setPreferredSize(new Dimension(200,28));
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(bladderBar, c);
		
		happyBar = new JProgressBar(0,100);
		happyBar.setPreferredSize(new Dimension(200,28));
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(happyBar, c);
		
		thirstBar = new JProgressBar(0,100);
		thirstBar.setPreferredSize(new Dimension(200,28));
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(thirstBar, c);
	}
	
	//This method adds the buttons to the iconPanel. 
	//These buttons are added along side the progressbars to indicate which action affects which bar.
	public static void addBotUseButtons(){
		
		GridBagConstraints d = new GridBagConstraints();
		
		feedButton = new JButton("Feed");
		feedButton.setPreferredSize(new Dimension(80,28));
		d.gridx = GridBagConstraints.RELATIVE;
		d.gridy = 0;
		iconPanel.add(feedButton, d);
		
		sleepButton = new JButton("Sleep");
		sleepButton.setPreferredSize(new Dimension(80,28));
		d.gridx = 2;
		d.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(sleepButton, d);
		
		batheButton = new JButton("Bathe");
		batheButton.setPreferredSize(new Dimension(80,28));
		d.gridx = 2;
		d.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(batheButton, d);
		
		interactButton = new JButton("Interact");
		interactButton.setPreferredSize(new Dimension(80,28));
		d.gridx = 2;
		d.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(interactButton, d);
		
		toiletButton = new JButton("Toilet");
		toiletButton.setPreferredSize(new Dimension(80,28));
		d.gridx = 2;
		d.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(toiletButton, d);
		
		playButton = new JButton("Play");
		playButton.setPreferredSize(new Dimension(80,28));
		d.gridx = 2;
		d.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(playButton, d);
		
		drinkButton = new JButton("Drink");
		drinkButton.setPreferredSize(new Dimension(80,28));
		d.gridx = 2;
		d.gridy = GridBagConstraints.RELATIVE;
		iconPanel.add(drinkButton, d);
		
	}
	
		//This method adds components to the specialPanel which is then added to the botPanel.
		//The specialPanel is used display the buttons unique to the shark.
		public static void addBotSpecButtons(){
			
			specialPanel = new JPanel();
			specialPanel.setLayout(null);
			specialPanel.setBackground(Color.darkGray);
			specialPanel.setBounds(360, 0, 220, 205);
			botPanel.add(specialPanel);
			
			huntButton = new JButton("Hunt");
			huntButton.setBounds(0,17,70,30);
			specialPanel.add(huntButton);
			
			scareButton = new JButton("Scare");
			scareButton.setBounds(130,17,70,30);
			specialPanel.add(scareButton);
			
			swimButton = new JButton("Swim");
			swimButton.setBounds(0,130,70,30);
			specialPanel.add(swimButton);
			
			rollButton = new JButton("Roll");
			rollButton.setBounds(130,130,70,30);
			specialPanel.add(rollButton);
			
			splashButton = new JButton("Splash");
			splashButton.setBounds(65,72,73,30);
			specialPanel.add(splashButton);
			
			pausePic = new ImageIcon("image/Dean-Shark/sharkpause.png");
			pauseButton = new JButton(pausePic);
			pauseButton.setBackground(null);
			pauseButton.setBorderPainted(false);
			pauseButton.setBounds(80, 160, 40, 40);
			specialPanel.add(pauseButton);
			
		}
		
		//This method adds components to the traitsPanel which is then added to the botPanel.
		//The traitsPanel is used display the traits that the shark has, (i.e name, age, breed, swim level).
		public static void addTraits(){
			
			traitPanel = new JPanel();
			traitPanel.setLayout(null);
			traitPanel.setBackground(Color.darkGray);
			traitPanel.setBounds(585,0,215,205);
			botPanel.add(traitPanel);
			
			traitsLabel = new JLabel("Traits:");
			traitsLabel.setForeground(Color.white);
			traitsLabel.setBounds(82, 3, 50, 10);
			traitPanel.add(traitsLabel);
			
			nameLabel = new JLabel("Name:");
			nameLabel.setForeground(Color.white);
			nameLabel.setBounds(10, 15, 180, 15);
			traitPanel.add(nameLabel);
			
			ageLabel = new JLabel("Age:");
			ageLabel.setForeground(Color.white);
			ageLabel.setBounds(10, 30, 50, 15);
			traitPanel.add(ageLabel);
			
			lifespanLabel = new JLabel("Lifespan:");
			lifespanLabel.setForeground(Color.white);
			lifespanLabel.setBounds(120, 30, 150, 15);
			traitPanel.add(lifespanLabel);
			
			breedLabel = new JLabel("Breed:");
			breedLabel.setForeground(Color.white);
			breedLabel.setBounds(10, 45, 180, 15);
			traitPanel.add(breedLabel);
			
			foodLabel = new JLabel("Favourite Food:");
			foodLabel.setForeground(Color.white);
			foodLabel.setBounds(10, 60, 150, 15);
			traitPanel.add(foodLabel);
			
			colourLabel = new JLabel("Colour:");
			colourLabel.setForeground(Color.white);
			colourLabel.setBounds(10, 75, 150, 15);
			traitPanel.add(colourLabel);
				
			genderLabel = new JLabel("Gender:");
			genderLabel.setForeground(Color.white);
			genderLabel.setBounds(120, 75, 150, 15);
			traitPanel.add(genderLabel);
			
			weightLabel = new JLabel("Weight:");
			weightLabel.setForeground(Color.white);
			weightLabel.setBounds(10, 90, 150, 15);
			traitPanel.add(weightLabel);
			
			lengthLabel = new JLabel("Length:");
			lengthLabel.setForeground(Color.white);
			lengthLabel.setBounds(120, 90, 150, 15);
			traitPanel.add(lengthLabel);
			
			teethLabel = new JLabel("Teeth:");
			teethLabel.setForeground(Color.white);
			teethLabel.setBounds(10, 105, 150, 15);
			traitPanel.add(teethLabel);
			
			finsLabel = new JLabel("Fins:");
			finsLabel.setForeground(Color.white);
			finsLabel.setBounds(10, 120, 150, 15);
			traitPanel.add(finsLabel);
			
			diffLabel = new JLabel("Difficulty:");
			diffLabel.setForeground(Color.white);
			diffLabel.setBounds(10, 135, 150, 15);
			traitPanel.add(diffLabel);
			
			skillLabel = new JLabel("Swim Skill Level:");
			skillLabel.setForeground(Color.white);
			skillLabel.setBounds(10, 150, 150, 15);
			traitPanel.add(skillLabel);
			
			saveButton = new JButton("Save");
			saveButton.setBounds(10, 172, 80, 28);
			traitPanel.add(saveButton);
			
			quitButton = new JButton("Quit");
			quitButton.setBounds(115, 172, 80, 28);
			traitPanel.add(quitButton);
			
		}
		
		//Shark Interface Label Getters
		
		public JLabel getNameLabel(){
			return nameLabel;
		}
		
		public JLabel getAgeLabel(){
			return ageLabel;
		}
		
		public JLabel getCoins(){
			return coinCountLabel;
		}
		
		public JLabel getBreedLabel(){
			return breedLabel;

		}
		
		public JLabel getFoodLabel(){
			return foodLabel;	
		}
		
		public JLabel getWeightLabel(){			
			return weightLabel;			
		}
		
		public JLabel getLengthLabel(){		
			return lengthLabel;		
		}
		
		public JLabel getTeethLabel(){		
			return teethLabel;		
		}
		
		public JLabel getFinsLabel(){		
			return finsLabel;
		}
		
		public JLabel getSkillLabel(){		
			return skillLabel;		
		}
		
		public JLabel getHealthLabel(){	
			return healthLabel;	
		}
		
		public JLabel getDiffLabel(){		
			return diffLabel;	
		}
		
		public JLabel getColourLabel(){
			return colourLabel;
		}
	
		public JLabel getGenderLabel(){
			return genderLabel;
		}
		
		public JLabel getPicLabel(){
			return picLabel;
		}
		
		public JLabel getLifespanLabel(){
			return lifespanLabel;
		}
		
		//Shark Interface Progressbar Getters
		
		public JProgressBar getHungerBar(){
			return hungerBar;
		}
		
		public JProgressBar getEnergyBar(){
			return energyBar;
		}
		
		public JProgressBar getHappyBar(){
			return happyBar;
		}
		
		public JProgressBar getSocialBar(){
			return socialBar;
		}
		
		public JProgressBar getHygieneBar(){
			return hygieneBar;
		}
		
		public JProgressBar getBladderBar(){
			return bladderBar;
		}
		
		public JProgressBar getThirstBar(){
			return thirstBar;
		}
		
		//Shark Interface Button Getters
		
		public JButton getFeedButton(){
			return feedButton;
		}
		
		public JButton getPlayButton(){
			return playButton;
		}
		
		public JButton getSleepButton(){
			return sleepButton;
		}
		
		public JButton getInteractButton(){
			return interactButton;
		}
		
		public JButton getBatheButton(){
			return batheButton;
		}
		
		public JButton getToiletButton(){
			return toiletButton;
		}
		
		public JButton getDrinkButton(){
			return drinkButton;
		}
		
		public JButton getSaveButton(){
			return saveButton;
		}
		
		public JButton getQuitButton(){
			return quitButton;
		}
		
		public JButton getShopButton(){
			return shopButton;
		}
		
		public JButton getHuntButton(){
			return huntButton;
		}
		
		public JButton getSwimButton(){
			return swimButton;
		}
		
		public JButton getScareButton(){
			return scareButton;
		}
		
		public JButton getSplashButton(){
			return splashButton;
		}
		
		public JButton getRollButton(){
			return rollButton;
		}
		
		public JButton getPauseButton(){
			return pauseButton;
		}
		
		//Shark Interface JFrame Getter
		
		public JFrame getSharkFrame(){
			return sharkFrame;
		}
		
		//Shark Interface Image Setters
		
		public void setSharkPic(String nImage){
			pic = new ImageIcon(nImage);
		}
		
		public void setSharkOverPic(String nImageO){
			picOver = new ImageIcon(nImageO);
		}
		
}
