
public class SharkTester {
	
	public static void main(String[] args){
		
		//Creating a new instance of shark object.
		Shark sid = new Shark("Sid", "Regular");
	
		System.out.println(sid.getPetName() + " the Shark has the following attributes:");
		System.out.println();
		System.out.println("Gender: " + sid.getGender());
		System.out.println("Breed: " + sid.getBreed() + " Shark");
		System.out.println("Colour: " + sid.getColour());
		System.out.println("Favourite Food: " + sid.getFood());
		System.out.println("It has " + sid.getTeeth() + " teeth.");
		System.out.println("It has " + sid.getFins() + " fins.");
		System.out.println("It weighs " + sid.getWeight() +"kg");
		System.out.println("It measures " + sid.getLength() + "m in length.");
		System.out.println();
	
		
		//Show goHunt() on shark's status
		sid.goHunt();	
		//This is what happens if the user tries to hunt after having already gone hunting
		sid.goHunt();
		//Swim method increases certain states and allows the shark to go hunting again
		sid.goSwim();
		sid.goHunt();
		//The splash method without swim skill level 5.
		sid.goSplash();
		//The splash method with swim skill 5.
		sid.setSwimSkill(5);
		sid.goSplash();
		//Showing goScare() method.
		sid.goScare();
		//Showing goRoll() method.
		sid.goRoll();
		//Showing grow() method.
		sid.grow();
		////Showing die() method on shark.
		sid.die();
	}
}
