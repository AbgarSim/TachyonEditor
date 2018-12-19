package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipDronesAbsentCond extends ShipDronesPresentCond {

	public ShipDronesAbsentCond() {
		super();
		type = ConditionType.SHIP_DRONES_ABSENT;
	}

}
