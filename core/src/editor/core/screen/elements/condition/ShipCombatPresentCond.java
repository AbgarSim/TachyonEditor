package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipCombatPresentCond extends ShipCondition {

	public ShipCombatPresentCond() {
		super();
		type = ConditionType.SHIP_COMBAT_PRESENT;
	}
	
	@Override
	public boolean conditionValid(){
		return true;
	}

}
