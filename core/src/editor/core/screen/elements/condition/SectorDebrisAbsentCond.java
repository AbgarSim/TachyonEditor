package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorDebrisAbsentCond extends SectorDebrisPresentCond {

	public SectorDebrisAbsentCond() {
		super();
		type = ConditionType.SECTOR_DEBRIS_ABSENT;
	}

}
