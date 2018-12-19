package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipCrewAbsentCond extends ShipCrewPresentCond {

	public ShipCrewAbsentCond() {
		super();
		type = ConditionType.SHIP_CREW_ABSENT;
	}

}
