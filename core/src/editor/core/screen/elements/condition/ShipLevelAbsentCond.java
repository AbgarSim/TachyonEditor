package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipLevelAbsentCond extends ShipLevelPresentCond {

	public ShipLevelAbsentCond() {
		super();
		type = ConditionType.SHIP_LEVEL_ABSENT;
	}

}
