package org.ucm.tp1;
import java.util.Scanner;
import org.ucm.tp1.logic.Level;
import org.ucm.tp1.control.Controller;
import org.ucm.tp1.logic.Game;

public class BuffyVampireSlayer {
		public static final String version = "1.0";
		public static final String usageMsg = "Usage: Vampire slayer <level> [seed]";
		public static final String welcomeMsg = String.format("Buffy the Vampire Slayer " + version + "%n");
		public static final String levelInfoMsg = "Level must be one of: " + Level.all(", ");
		public static final String seedIsNumberMsg = "the seed must be a number";
		public static final String seedInfoMsg = "Random generator initialized with seed: ";

	public static void main(String[] args) {
		if (args.length < 1 || args.length > 2)
			System.out.print(usageMsg);
		else {
			Level level = Level.parse(args[0]);
			if(level == null) {
				 System.out.println(usageMsg);
				 System.out.println(levelInfoMsg);
			}
			else {
				Long seed;
				try {
					if (args.length == 2)						
						seed = Long.parseLong(args[1]);
					else
						seed = System.currentTimeMillis();
					
					System.out.print(welcomeMsg);
					System.out.println(seedInfoMsg + seed);
					
					Controller controller = new Controller(new Game(seed, level), new Scanner(System.in));
					controller.run();
				}
				catch (NumberFormatException nfe) {
						System.out.println(usageMsg + ": " + seedIsNumberMsg);
				}
			}
		}
	}
}