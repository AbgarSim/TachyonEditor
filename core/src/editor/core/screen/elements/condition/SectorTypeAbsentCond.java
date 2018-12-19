package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorTypeAbsentCond extends SectorTypePresentCond {

	public SectorTypeAbsentCond() {
		super();
		type = ConditionType.SECTOR_TYPE_ABSENT;
	}

}
