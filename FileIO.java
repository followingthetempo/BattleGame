import java.io.*;
import java.util.ArrayList;

public class FileIO {

	public static void main(String[] args) throws IOException {
		
		System.out.println(readCharacter("monster.txt"));
		System.out.println(readCharacter("player.txt"));
		System.out.println(readSpells("spells.txt"));
		Character character=new Character("Tempo",13.0,25.0,0);
		writeCharacter("player.txt",character);
	}
	//fileIO method for an info of a Character
	public static Character readCharacter(String fileName) {
		//reads appropriate character file and adds each line into an arraylist of strings. 
		try {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		//initialize the attributes arraylist
		ArrayList<String> attributes=new ArrayList<String>();
		String currentLine =br.readLine();
		while(currentLine != null) {
			attributes.add(currentLine);
			currentLine = br.readLine();
		}
		br.close();
		fr.close();
		
		//initializing a new character
		Character charInfo=new Character("null",0,0,0);
		//for loop to go through the arraylist of characters' attributes 
			for (int i=0; i<attributes.size();i++) {
				//assigning each element in attributes to a variable accordingly
				//use this information to create the new character. 
				String name=attributes.get(0);
				double attack=Double.parseDouble(attributes.get(1));
				double maxHealth=Double.parseDouble(attributes.get(2));
				int wins=Integer.parseInt(attributes.get(3));
				charInfo=new Character(name,attack,maxHealth,wins);
			}
			return charInfo;
			
		}catch(FileNotFoundException e){
	        throw new IllegalArgumentException("File cannot be found!");
	        
	    }catch(IOException e){
	        throw new IllegalArgumentException("FileIO exception.");
	    }
	}
	//fileIO method for new spells
	public static ArrayList<Spell> readSpells(String fileName) {
		//read the spells file and adds each line into an arraylist to get spellinfo
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			//initialize the arraylist of spells and a string[] 
			ArrayList<String> spells=new ArrayList<String>();
			String[]parts=new String[spells.size()];
			String currentLine =br.readLine();
			while(currentLine != null) {
				spells.add(currentLine);	
				currentLine = br.readLine();
			}	
			br.close();
			fr.close();
			
			//initializing a new arraylist of spells 
			ArrayList<Spell> spellInfo=new ArrayList<Spell>();
			//loop to go through arraylist, then split each spell and add it into parts
			for (int i=0;i<spells.size();i++) {
				parts=new String[spells.size()];
				parts=spells.get(i).split(" ");
				//assign each attribute of spells to a variable 
				String spellName=parts[0];
				double min=Double.parseDouble((parts[1]));
				double max=Double.parseDouble((parts[2]));
				double success=Double.parseDouble((parts[3]));
				//create a new spell out of the above info and add it into the spell arraylist.
				Spell spellList=new Spell(spellName,min,max,success);
				spellInfo.add(spellList);
			}
			return spellInfo;
			
	}catch(FileNotFoundException e){
        throw new IllegalArgumentException("File cannot be found!");
        
    }catch(IOException e){
        throw new IllegalArgumentException("FileIO exception.");
    	}
	}
	//fileIO method for updating a characters info 
	  public static void writeCharacter(String fileName, Character character){
		  //writes the character attributes to a file
		    try{
		      FileWriter fw = new FileWriter(fileName);
		      BufferedWriter bw = new BufferedWriter(fw);
		      bw.write(character.getName()+"\n");
		      bw.write(character.getAttack()+"\n");
		      bw.write(character.getMax()+"\n");
		      bw.write(character.getWins()+"\n");
		      
		      bw.close();
		      fw.close();
		
		    }catch(FileNotFoundException e) {
		    	throw new IllegalArgumentException("File cannot be found!");
		    }catch(IOException e) {
		    	throw new IllegalArgumentException("FileIO Exception.");
		    }
	  }
}
		    
		 
