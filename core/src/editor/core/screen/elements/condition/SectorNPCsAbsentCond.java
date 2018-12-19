package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorNPCsAbsentCond extends SectorNPCsPresentCond {

	public SectorNPCsAbsentCond() {
		super();
		type = ConditionType.SECTOR_NPCS_ABSENT;
	}

}
