package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipHullAbsentCond extends ShipHullPresentCond {

	public ShipHullAbsentCond() {
		super();
		type = ConditionType.SHIP_HULL_ABSENT;
	}

}
