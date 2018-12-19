package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorShipsAbsentCond extends SectorShipsPresentCond {

	public SectorShipsAbsentCond() {
		super();
		type = ConditionType.SECTOR_SHIPS_ABSENT;
	}

}
