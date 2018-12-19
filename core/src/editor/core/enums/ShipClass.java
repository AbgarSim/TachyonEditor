package editor.core.enums;

public enum ShipClass {
	UNKNOWN(0),
	STATION(1),
	LIGHT_SCOUT(2),
	HEAVY_SCOUT(3),
	INTERCEPTOR(4),
	CRUISER(5),
	BATTLESHIP(6),
	ASTEROID(7),
	TRADER(8),
	PLATFORM(9),
	CORVETTE(10),
	CARRIER(11),
	DROPSHIP(12),
	TRANSPORT(13),
	FREIGHTER(14),
	FIGHTER(15),
	BOMBER(16),
	FRIGATE(17),
	DREADNOUGHT(18),
	CREATURE(19),
	MOTHERSHIP(20);

    public int ID;
    
    ShipClass(int inputID){
		ID=inputID;
	}

}
