package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipSystemAbsentCond extends ShipSystemPresentCond {

	public ShipSystemAbsentCond() {
		super();
		type = ConditionType.SHIP_SYSTEM_ABSENT;
	}

}
