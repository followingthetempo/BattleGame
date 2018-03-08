import java.util.Scanner;

public class BattleGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		playGame();
	}
	//the main play game method	
	public static void playGame() {
		//initialize a new spell and prints out the available spells a character can cast
		//gets the spells using the fileIO readSpells method, and sets them using setSpells in Character
		Spell spellList=new Spell ("null",0,0,0);
		System.out.println("Available Spells: ");
		for (int i=0;i<(FileIO.readSpells("spells.txt")).size();i++) {
			spellList=FileIO.readSpells("spells.txt").get(i);
			System.out.println(spellList.toString());
			Character.setSpells(FileIO.readSpells("spells.txt"));
		}
		//prints out the info of the player.
		Character player=FileIO.readCharacter("player.txt");
		player.printCharacter();
		//prints out the info of the enemy. 
		Character enemy=FileIO.readCharacter("monster.txt");
		enemy.printCharacter();

		//user input 
		String command;
		Scanner read=new Scanner(System.in);

		//game runs until either the player or enemy's health gets to 0 or less
		while (player.getHealth()>0 && enemy.getHealth()>0) {
			System.out.println("Attack or Quit?");
			command=read.nextLine();

			//actions for when the user inputs attack
			if (command.equals("attack")||command.equals("Attack")) {
				//calls doAttack, with the player attacking.
				doAttack(player,enemy);
				//if the enemy is defeated, the player wins.
				//increaseWins to increase the player's wins, and fileIO rewrites the win attribute accordingly. 
				if (enemy.getHealth()<=0) {
					System.out.println("Congratulations " + player.getName()+", you have won!");
					player.increaseWins();
					FileIO.writeCharacter("player.txt",player);
					break;
				}
				//calls doAttack, with the enemy attacking.
				doAttack(enemy,player);
				//if player is defeated, enemy wins.
				//increaseWins to increase the enemy's wins, and fileIO rewrites the win attribute accordingly. 
				if (player.getHealth()<=0) {
					System.out.println("Oh no, "+player.getName()+ ", "+enemy.getName()+ " has defeated you!");
					enemy.increaseWins();
					FileIO.writeCharacter("monster.txt",enemy);
				}

				//if the user inputs quit, the game ends. 
			}else if (command.equals("quit")||command.equals("Quit")) {
				System.out.println("Goodbye!");
				return;
				//all other inputs will cause the player to cast a spell. 
			}else{
				//calls castSpell with the command string as input and compares it to the list of spells
				double spellDamage=player.castSpell(command);
				//uses takeDamage and castSpell's returned value to calculate damage done to enemy if spell is cast. 
				enemy.takeDamage(spellDamage);
				if (enemy.getHealth()>0) {
					System.out.println(enemy.getName()+" took " +spellDamage+" damage and now has "+enemy.getHealth()+" health left!");
					//calling doAttack so the enemy can attack after the player attempts to cast a spell. 
					doAttack(enemy,player);

					//similar to the attack command, this is for when the enemy is knocked out by a spell. 
				}else if(enemy.getHealth()<=0) {
					System.out.println(enemy.getName()+" has no health left!");
				}

				//for if the enemy is knocked out with a spell, or if the player is knocked out after casting a spell.
				if (enemy.getHealth()<=0) {
					System.out.println("Congratulations " + player.getName()+", you have won!");
					player.increaseWins();
					FileIO.writeCharacter("player.txt",player);
					break;
				}
				if (player.getHealth()<=0) {
					System.out.println("Oh no, "+player.getName()+ ", "+enemy.getName()+ " has defeated you!");
					enemy.increaseWins();
					FileIO.writeCharacter("monster.txt",enemy);
				}
			}
		}
	}

	//method for calculating damage done and the characters' new health
	public static void doAttack(Character char1, Character char2) {
		//damage is taken with the calcAttack method in character and the first character's attack power. 
		double damage= char1.calcAttack(char1.getAttack());
		System.out.println(char1.getName()+" dealt "+damage+" damage!");
		//using takedamage, the damage value is deducted from the character2's current health
		char2.takeDamage(damage);

		//print statements to print the characters' current healths. 
		if (char2.getHealth()>0) {
			System.out.println(char2.getName()+" has "+char2.getHealth()+" health left!");


		}else if (char2.getHealth()<=0) {
			System.out.println(char2.getName()+" has no health left!");
		}
	}

}

