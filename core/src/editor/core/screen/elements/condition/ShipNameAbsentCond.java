package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipNameAbsentCond extends ShipNamePresentCond {

	public ShipNameAbsentCond() {
		super();
		type = ConditionType.SHIP_NAME_ABSENT;
	}

}
