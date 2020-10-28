package org.ucm.tp1.logic;

public enum Level {
	EASY("easy", 3, 0.1, 8, 4), HARD("hard", 5, 0.2, 7, 3), INSANE("insane", 10, 0.3, 5, 6);

	private String name;
	private int numberOfVampires;
	private double vampireFrequency;
	private int dim_x, dim_y;

	private Level(String name, int numberOfVampires, double vampireFrequency, int dim_x, int dim_y) {
		this.name = name;
		this.numberOfVampires = numberOfVampires;
		this.vampireFrequency = vampireFrequency;
		this.dim_x = dim_x;
		this.dim_y = dim_y;
	}

	// TODO fill your code
    public int getDim_x() {
		return dim_x;
	}

	public int getDim_y() {
		return dim_y;
	}

	//code
	
	public int getNumberOfVampires() {
		return numberOfVampires;
	}

	public void setNumberOfVampires(int numberOfVampires) {
		this.numberOfVampires = numberOfVampires;
	}

	double getVampireFrequency() {
		return vampireFrequency;
	}

		
	//code



	public static Level parse(String inputString) {
        for (Level level : Level.values())
            if (level.name().equalsIgnoreCase(inputString)) 
            	return level;
        return null;
    }
    
    public static String all(String separator) {
        String allLevels = "";
        for (Level level : Level.values())
            allLevels += level.name() + separator;
        return allLevels.substring(0, allLevels.length() - separator.length());
    }

	
}
