package editor.core.screen.elements.condition;

public enum ShipSystemSuperType {
	OTHER(0),
	UNKNOWN(1),
	CAPACITOR(2),
	REACTOR(3),
	ENGINES(4),
	HYPERDRIVE(5),
	PILOTING(6),
	SHIELDS(7),
	MEDICAL(8),
	DOOR_CONTROL(9),
	DRONE_CONTROL(10),
	LASER_DEFENSE(11),
	LASER_WEAPONS(12),
	MISSILE_WEAPONS(13),
	SHIPYARD(14),
	SENSORS(15),
	WEAPONS_CONTROL(16),
	OXYGEN(17),
	REPAIR(18),
	BEAM_WEAPONS(19),
	TELEPORT(20),
	MIND_CONTROL(21),
	CLOAKING(22),
	CONSUMABLES_SHOP(23),
	SYSTEMS_SHOP(24),
	SHIPS_SHOP(25);

	public int ID;
    
    ShipSystemSuperType(int inputID){
		ID=inputID;
	}
}
