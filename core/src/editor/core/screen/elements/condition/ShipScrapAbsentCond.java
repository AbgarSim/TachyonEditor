package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipScrapAbsentCond extends ShipScrapPresentCond {

	public ShipScrapAbsentCond() {
		super();
		type = ConditionType.SHIP_SCRAP_ABSENT;
	}

}
