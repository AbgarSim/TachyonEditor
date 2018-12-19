package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipShieldsAbsentCond extends ShipShieldsPresentCond {

	public ShipShieldsAbsentCond() {
		super();
		type = ConditionType.SHIP_SHIELDS_ABSENT;
	}

}
