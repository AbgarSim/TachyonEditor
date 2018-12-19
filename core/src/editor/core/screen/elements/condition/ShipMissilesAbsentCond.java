package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipMissilesAbsentCond extends ShipMissilesPresentCond {

	public ShipMissilesAbsentCond() {
		super();
		type = ConditionType.SHIP_MISSILES_ABSENT;
	}

}
