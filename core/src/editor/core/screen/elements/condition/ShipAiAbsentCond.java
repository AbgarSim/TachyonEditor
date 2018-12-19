package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipAiAbsentCond extends ShipAiPresentCond {

	public ShipAiAbsentCond() {
		super();
		type = ConditionType.SHIP_AI_ABSENT;
	}

}
