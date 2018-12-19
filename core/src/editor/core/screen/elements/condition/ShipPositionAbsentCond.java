package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipPositionAbsentCond extends ShipPositionPresentCond {

	public ShipPositionAbsentCond() {
		super();
		type = ConditionType.SHIP_POSITION_ABSENT;
	}

}
