package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipCargoAbsentCond extends ShipCargoPresentCond {

	public ShipCargoAbsentCond() {
		super();
		type = ConditionType.SHIP_CARGO_ABSENT;
	}

	@Override
	public boolean conditionValid() {
		return cargoType!=null && (systemType!=null || (systemModel!=null && !systemModel.isEmpty()));
	}

}
