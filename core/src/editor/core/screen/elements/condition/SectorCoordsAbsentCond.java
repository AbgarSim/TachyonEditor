package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorCoordsAbsentCond extends SectorCoordsPresentCond {

	public SectorCoordsAbsentCond() {
		super();
		type = ConditionType.SECTOR_COORDS_ABSENT;
	}

}
