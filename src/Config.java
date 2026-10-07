import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/* Yakup Efe Akça 150124078
 * Config Class:
   This class is designed to read the main configuration file of the game 
   and store these settings in a HashMap structure that can be accessed 
   from anywhere within the game engine.
 */

public class Config {
	HashMap<String, Integer> map;
	
	/* Yakup Efe Akça 150124078
	 * Constructor of the Config Class.
	 * * When this method is initialized, it performs the following operations:
	 * 1. Opens the text file located in the "src/config" directory.
	 * 2. Reads the file line by line and skips any empty lines.
	 * 3. Splits each valid line into two parts using the ":" character.
	 * 4. Adds the left part as the 'Key' and the right part as the 'Value' into the HashMap.
	 * 5. Catches any reading errors (e.g., file not found) and prints the error to the console.
	 */
	
	public Config() {
		map = new HashMap<String, Integer>();
		try(BufferedReader reader = new BufferedReader(new FileReader("src/config"))) {
			String line;
			while((line = reader.readLine()) != null) {
				if(line.trim().isEmpty()) {
					continue;
				}
				String[] lineArray = line.split(":");
				map.put(lineArray[0], Integer.parseInt(lineArray[1].trim()));	
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}