import java.util.Random;

public class Spell {

	//private spell attributes
	private String spellName;
	private double minDam;
	private double maxDam;
	private double success;

	//constructor
	public Spell(String name,double min, double max, double success) {
		this.spellName=name;
		this.minDam=min;
		this.maxDam=max;
		this.success=success;

		//limits for the minDamage and the chance of success, throwing an exception when neccessary. 
		boolean minLimit=(min>max)||(min<0);
		boolean successLimit=(success<0)||(success>1);

		if (minLimit||successLimit) {
			throw new IllegalArgumentException ("exception");
		}
	}
	//returns spell's name
	public String getName() {
		return this.spellName;
	}
	//returns spell's damage. randomly generates a damage value btwn 0 and 1
	//if this value is higher than the success rate, damage is 0.
	//else, the damage will be between the min damage and max damage.
	public double getDamage() {
		Random damage=new Random();
		double mult=damage.nextDouble();

		if (mult>this.success) {
			mult=0.0;
			return mult;	
		}else{
			mult=(damage.nextDouble()*(this.maxDam-this.minDam))+this.minDam;	
		}
		return mult;
	}
	//to string method to print out the info of the spell. 
	public String toString() {
		double successPerc=this.success*100;
		String s="Name: "+spellName+" Min Damage: "+minDam+" Max Damage: "+maxDam+" Success rate: "+successPerc+"%";
		return s;
	}		
}
