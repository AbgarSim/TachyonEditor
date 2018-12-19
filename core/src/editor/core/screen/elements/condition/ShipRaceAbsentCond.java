package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipRaceAbsentCond extends ShipRacePresentCond {

	public ShipRaceAbsentCond() {
		super();
		type = ConditionType.SHIP_RACE_ABSENT;
	}

}
