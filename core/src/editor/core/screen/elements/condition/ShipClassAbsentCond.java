package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipClassAbsentCond extends ShipClassPresentCond {

	public ShipClassAbsentCond() {
		super();
		type = ConditionType.SHIP_CLASS_ABSENT;
	}
	
}
