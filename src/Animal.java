//import javax.swing.JOptionPane;

public class Animal {
	
	protected static String name;
	protected static String birthday;
	private boolean alive;
	

	public String[] difficulty = {"Recruit", "Regular", "Hardened"};
	protected static String selectedDif;
	
	//states min and max; max to be declared depending on difficulty chosen.
	private final int MIN = 0;
	private final int MAX = 100;
	

	private int energy, fun,social,hunger,thirst, bladder, hygiene, health, mood;
	private Double age;
	
	//private Elephant newElephant;
	//private Monkey newMonkey;
	
	private String moodStatus, energyStatus, funStatus, socialStatus, hungerStatus, thirstStatus, bladderStatus, hygieneStatus, healthStatus, aliveStatus;
		
	//Rates of decrease, method with class will initialise these on the difficulty level chosen
	private double highrate, midrate, lowrate;
	
	//Animal Constructor - initialises animals name, birthday, difficulty.
	public Animal(String name, String set){
		
		
		//name is initialised
		this.name = name;
		//pet type is initialised
	
		this.selectedDif = set;
		//birthday is initialised
		
		//this.birthday = birthday;
		
		alive 		= true;
		//animal states are initialised
		mood 		= 100;
		energy 		= 100;
		social  	= 100;
		fun  		= 100;
		hunger  	= 100;
		thirst  	= 100;
		bladder  	= 100;
		hygiene  	= 100;
		
		health 		= 100;
		
		age = 0.0;
		setRates();	
	}
	
	public String getPetName(){
		return name;
	}
	public void setPetName(String name){
		this.name = name;
	}
	
	public double getAge(){
		return age;
	}
	public void setAge(double age){
		this.age = age;
	}
	public void incrAge(double increase){
		age+=increase;
	}
	public void decrAge(double decrease){
		age-=decrease;
	}
	
	public String getBirthday(){
		return birthday;
	}
		
	public String getDifficulty(){
		return selectedDif;
	}
	public void setDifficulty(String level ){
		selectedDif = level;
	}
	
	public boolean getAlive(){
		return alive;
	}
	public void setAliveFalse(){
		alive=false;
	}
	public void setAliveTrue(){
		alive=true;
	}
	
 	public void setRates(){
		if(selectedDif == "Recruit"){
			highrate = 0.5;
			midrate = 1;
			lowrate = 1.5;
		}else if(selectedDif == "Regular"){
			highrate = 1;
			midrate = 1.5;
			lowrate = 2;
		}else if(selectedDif ==  "Hardened"){
			highrate = 1.5;
			midrate = 2;
			lowrate = 2.5;
		}
	}
	
	//Animal State Getters	
		public int getMood(){
			
			return mood;
	}
		
		public int getEnergy(){
			return energy;
		}
		public int getFun(){
			return fun;
		}
		public int getSocial(){
			return social;
		}
		public int getHunger(){
			return hunger;
		}
		public int getThirst(){
			return thirst;
		}
		public int getBladder(){
			return bladder;
		}
		public int getHygiene(){
		return hygiene;
	}
	
		public int getHealth(){
			return health;
		}
		
		//get min and max values
		public int getMin(){
			return MIN;
		}
		public int getMax(){
			return MAX;
		}
		
	//Animal State Setters
		public void setEnergy(int energy){
			this.energy=energy;
		}
		public void setFun(int fun){
			this.fun=fun;
		}
		public void setSocial(int social){
			this.social=social;
		}
		public void setHunger(int hunger){
			this.hunger=hunger;
		}
		public void setThirst(int thirst){
			this.thirst=thirst;
		}
		public void setBladder(int bladder){
			this.bladder=bladder;
		}
		public void setHygiene(int hygiene){
			this.hygiene=hygiene;
		}
		
		public void setHealth(int health){
			this.health = health;
		}
	//Animal States mutators - increment 
		public void incrEnergy(int increase){
			energy+=increase;
		}
		public void incrFun(int increase){
			fun+=increase;
		}
		public void incrSocial(int increase){
			social+=increase;
		}
		public void incrHunger(int increase){
			hunger+=increase;
		}
		public void incrThirst(int increase){
			thirst+=increase;
		}
		public void incrBladder(int increase){
			bladder+=increase;
		}
		public void incrHygiene(int increase){
			hygiene+=increase;
		}
		
		public void incrHealth(int increase){
			health+=increase;
		}
	//Animal States mutators - decrement 
		public void decrEnergy(int decrease){
				energy-=decrease;
			}
		public void decrFun(int decrease){
				fun-=decrease;
			}
		public void decrSocial(int decrease){
				social-=decrease;
			}
		public void decrHunger(int decrease){
				hunger-=decrease;
			}
		public void decrThirst(int decrease){
				thirst-=decrease;
			}
		public void decrBladder(int decrease){
				bladder-=decrease;
			}
		public void decrHygiene(int decrease){
				hygiene-=decrease;
			}
	
		public void decrHealth(int decrease){
			health-=decrease;
		}
		
//Pet State Status	for testing
	public void petStates(){
		
		moodStatus();
		energyStatus();
		funStatus();
		socialStatus();
		hungerStatus();
		thirstStatus();
		bladderStatus();
		hygieneStatus();
		healthStatus();
		aliveStatus();
		
	}
	
	//status getters
	public String getMoodStatus(){
		moodStatus();
		return moodStatus;
	}
	public String getEnergyStatus(){
		energyStatus();
		return energyStatus;
	}
	public String getSocialStatus(){
		socialStatus();
		return socialStatus;
	}
	public String getFunStatus(){
		funStatus();
		return funStatus;
	}
	public String getHungerStatus(){
		hungerStatus();
		return hungerStatus;
	}
	public String getThirstStatus(){
		thirstStatus();
		return thirstStatus;
	}
	public String getBladderStatus(){
		bladderStatus();
		return bladderStatus;
	}
	public String getHygieneStatus(){
		hygieneStatus();
		return hygieneStatus;
	}
	
	public String getHealthStatus(){
		healthStatus();
		return healthStatus;
	}
	public String getAliveStatus(){
		aliveStatus();
		return aliveStatus;
	}
	
	//status setters
 	public void moodStatus(){
 		mood = (energy + social + fun + hunger + thirst + bladder + hygiene)/7;
 		if(mood>50){
			moodStatus = "Happy";
		}else{
			moodStatus = "Sad";
		}
	}
	public void healthStatus(){
		
		if(health==100){
			healthStatus="100%";
		}else if(health<100 && health >= 90){
			healthStatus="Excellent";
		}else if(health<90 && health >= 80){
			healthStatus=":))";
		}else if(health<80 && health >= 70){
			healthStatus=":)";
		}else if(health<70 && health >= 60){
			healthStatus="Very Good";
		}else if(health<60 && health >= 50){
			healthStatus="Good";
		}else if(health<50 && health >= 40){
			healthStatus="Fair";
		}else if(health<40 && health >= 30){
			healthStatus="Poor";
		}else if(health<30 && health >= 20){
			healthStatus="Help";
		}else if(health<20 && health >= 10){
			healthStatus="Dying";
		}else if(health<10 && health >= 1){
			healthStatus="Reach for the light";
		}else if(health<100 && health >= 90){
			healthStatus="Dead - Flatline";
			die();
		}
	}
	public void energyStatus(){
		if(energy >70){
			energyStatus = "Buzzing :D";
		}else if(energy <= 70 && energy >30){
			energyStatus = ":)";
		}else{
			energyStatus = "Agh! Need Sleep :|";
		}
	}
	public void funStatus(){
		if(fun >70){
			funStatus = " :D";
		}else if(fun <= 70 && fun >30){
			funStatus = ":)";
		}else{
			funStatus = "Bored :|";
		}
	}
	public void socialStatus(){
		if(social >70){
			socialStatus = " BFF's :D";
		}else if(social <= 70 && social >30){
			socialStatus = ":)";
		}else{
			socialStatus = "Alone :|";
		}
	}
	public void hungerStatus(){
		if(hunger >70){
			hungerStatus = "Food Coma :D";
		}else if(hunger <= 70 && hunger >30){
			hungerStatus = ":)";
		}else{
			hungerStatus = "Starved :|";
		}
	}
	public void thirstStatus(){
		if(thirst >70){
			thirstStatus = " Watered (Y) :D";
		}else if(thirst <= 70 && thirst >30){
			thirstStatus = ":)";
		}else{
			thirstStatus = "While Dry :|";
		}
	}
	public void bladderStatus(){
		if(bladder >70){
			bladderStatus = " Empty :D";
		}else if(bladder <= 70 && bladder >30){
			bladderStatus = ":)";
		}else{
			bladderStatus = "Busten :|";
		}
	}
	public void hygieneStatus(){
		if(hygiene >70){
			hygieneStatus = " Scrubed :D";
		}else if(hygiene <= 70 && hygiene >30){
			hygieneStatus = ":)";
		}else{
			hygieneStatus = "Stinken :|";
		}
	}
	public void aliveStatus(){
		if(alive==true){
			aliveStatus= "Alive";
		}else{
			aliveStatus= "Dead";
		}
	}
	
	//Idle Pet - state decrease
	public void idlePet(){
			
			decEnergy();
			decFun();
			decSocial();
			decHunger();
			decThirst();
			decHygiene();
			decBladder();
			
			
		
		
	}
	public void decEnergy()
	{
			if(energy > 80){
				energy -= highrate;
			}else if(energy > 30 && energy <=80){
				energy -= midrate;
			}else if(energy <=30){
				energy -= lowrate;
			}
	}
	public void decFun()

	{
			if(fun > 80){
				fun -= highrate;
			}else if(fun > 30 && fun <=80){
				fun -= midrate;
			}else if(fun <=30){
				fun -= lowrate;
			}		
	}
	public void decSocial()
	{
			if(social > 80){
				social -= highrate;
			}else if(social > 30 && social <=80){
				social -= midrate;
			}else if(social <=30){
				social -= lowrate;
			}	
	}
	public void decHunger()
	{
			if(hunger > 80){
				hunger -= highrate;
			}else if(hunger > 30 && hunger <=80){
				hunger -= midrate;
			}else if(hunger <=30){
				hunger -= lowrate;
			};
			
			
			
	}
	public void decThirst()
	{
			if(thirst > 80){
				thirst -= highrate;
			}else if(thirst > 30 && thirst <=80){
				thirst -= midrate;
			}else if(thirst <=30){
				thirst -= lowrate;
			}		
	}
	public void decBladder()
	{
			if(bladder > 80){
				bladder -= highrate;
			}else if(bladder > 30 && bladder <=80){
				bladder -= midrate;
			}else if(bladder <=30){
				bladder -= lowrate;
			}		
	}	
	public void decHygiene()
	{
			if(hygiene > 80){
				hygiene -= highrate;
			}else if(hygiene > 30 && hygiene <=80){
				hygiene -= midrate;
			}else if(hygiene <=30){
				hygiene -= lowrate;
			}		
	}

	//Animal Actions
		public void sleep(){
			System.out.println("\nSleeping...");
			
				energy+=5;	
		}
		public void play(){
			
				energy-=4;
				fun+=5;
				hunger-=3;
				thirst-=2;
				hygiene-=3;		
		}
		public void interact(){
			
				energy-=2;
				social+=5;
				thirst-=2;
				bladder-=2;
		}
		public void feed(){
				hunger+=5;
				thirst-=2;
				hygiene-=2;
		}
		public void water(){
				hunger-=4;
				thirst+=5;
				bladder-=5;
			}
				
		public void toilet(){

				hygiene-=2;
				bladder+=5;
		}
		public void bathe(){
			System.out.println("\nWashing...");
				hygiene+=5;
		}

	public void die(){
		alive=false;
		energy=0;
		social=0;
		fun=0;
		hunger=0;
		thirst=0;
		bladder=0;
		hygiene=0;		
	}
	
	
	public void stateMinMax(){
		if(energy<MIN){
			energy=MIN;
			health-=5;
		}else if(energy>100){
			energy=MAX;
			health+=10;
		}
		if(fun<MIN){
			fun=MIN;
			health-=5;
		}else if(fun>100){
			fun=MAX;
			health+=10;
		}
		if(social<MIN){
			social=MIN;
			health-=5;
		}else if(social>100){
			social=MAX;
			health+=10;
		}
		if(hunger<MIN){
			hunger=MIN;
			health-=5;
		}else if(hunger>100){
			hunger=MAX;
			health+=10;
		}
		if(thirst<MIN){
			thirst=MIN;
		}else if(thirst>100){
			thirst=MAX;
		}
		if(bladder<MIN){
			bladder=MIN;
			health-=5;
		}else if(bladder>100){
			bladder=MAX;
			health+=10;
		}
		if(hygiene<MIN){
			hygiene=MIN;
			health-=5;
		}else if(hygiene>100){
			hygiene=MAX;
			health+=10;
		}
		if(health<=0){
			die();
		}else if(health>100){
			health=100;
		}
	}
	

	//Animal Details
	public void animalDetails(){
		System.out.println("Pet Name: " + getPetName() + "\nBirthday: " + getBirthday() + "\nAlive: " + getAlive() +"\n");
	}
		public void animalState(){
		
		System.out.print("Feeling: " + getMoodStatus() + "\t\tHealth: " + getHealth()
				+ "\nEn: " + getEnergy()
				+ " Fn: " + getFun()
				+ " So: " + getSocial() 
				+ " Hu: " +  getHunger() 
				+ " Th: " + getThirst()
				+ " Bl: " +  getBladder() 
				+ " Hy: " + getHygiene() + "\n");
	}
	
	
	
}
