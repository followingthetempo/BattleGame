import java.util.ArrayList;
import java.util.Random;

public class Character {
	//private character attributes
	private String name;
	private double attack;
	private double maxHealth;
	private double currentHealth;
	private int wins;
	private static ArrayList<Spell> spells=FileIO.readSpells("spells.txt");

	//constructor 	
	public Character(String name, double attackValue, double maxHealth, int wins) {
		this.name=name;
		this.attack=attackValue;
		this.maxHealth=maxHealth;
		this.currentHealth=maxHealth;
		this.wins=wins;
	}

	//returns character's name
	public String getName() {
		return this.name;
	}

	//returns character's maximum health
	public double getMax() {
		return this.maxHealth;
	}

	//returns character's attack value
	public double getAttack() {
		return this.attack;	
	}

	//returns character's current health
	public double getHealth() {
		return this.currentHealth;
	}

	//returns the character's amount of wins
	public int getWins() {
		return this.wins;	
	}
	//to string method that indicates the name and health of the character 
	public String toString() {
		String name=getName();
		String message=name+" has "+getHealth()+" health!";
		return message;

	}
	//calculates the damage done by a character by taking their attack value multiplying it by a random value btwn 0.3 and 0.7
	public double calcAttack(double attack) {
		Random damageCalc=new Random();
		double multiplier=(damageCalc.nextDouble()*0.4)+0.3;
		double damage=multiplier*attack; 
		return damage;
	}
	//deducts the damage value from character's current health and changes it accordingly 
	public void takeDamage(double damage) {
		double damageDone=this.currentHealth-damage;
		currentHealth=damageDone;
	}

	//method for increasing number of wins of a character
	public void increaseWins() {
		int wins=this.wins;
		wins++;
		this.wins=wins;

		System.out.println("Number of wins: "+wins);
	}
	//method for printing out the character's info
	public void printCharacter() {

		System.out.println("Character name: "+name);
		System.out.println("Current Health: "+currentHealth);
		System.out.println("Attack: "+attack);
		System.out.println("Number of Wins: "+wins);
	}

	//set method for spells, copies a list of spells into a new arraylist of spells 
	public static void setSpells(ArrayList<Spell> spells) {
		//initialize a new arraylist of spells with size of spells
		//loop to go through this arraylist and then add the elements in spells into it
		ArrayList<Spell> spells2=new ArrayList<Spell>(spells.size());
		for (int i=0;i<spells2.size();i++) {
			spells2.add(spells.get(i));
			spells=spells2;	
		}
	}
	//method that goes through the list of spells and prints out various statements depending on if the input name matches with a spell name in the arraylist
	public double castSpell(String spellName) {
		//initializing the damage variable
		double damage=0.0;
		//for loop to go through arraylist of spells and getting the names and damage
		for (int i=0;i<spells.size();i++) {
			String name=spells.get(i).getName();
			damage=spells.get(i).getDamage();

			//conditions for battleGame
			if (name.equalsIgnoreCase(spellName)) {
				damage=spells.get(i).getDamage();
				if (damage==0) {
					System.out.println(this.name+"'s spell "+name+ " failed to cast!");
					return damage;
				}else if(damage>0) {
					System.out.println(this.name+"'s spell "+name+" was cast!");
					return damage;
				}else if (name.equalsIgnoreCase(spellName)==false){
					System.out.println("Why did you cast an uknown spell?!");
					return 0.0;
				}
			}
		}
		return damage;
	}	
}
