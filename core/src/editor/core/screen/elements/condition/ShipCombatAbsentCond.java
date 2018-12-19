package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipCombatAbsentCond extends ShipCombatPresentCond {

	public ShipCombatAbsentCond() {
		super();
		type = ConditionType.SHIP_COMBAT_ABSENT;
	}

}
