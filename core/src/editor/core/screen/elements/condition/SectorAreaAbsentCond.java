package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorAreaAbsentCond extends SectorAreaPresentCond {

	public SectorAreaAbsentCond() {
		super();
		type = ConditionType.SECTOR_AREA_ABSENT;
	}
	
}
