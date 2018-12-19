package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorThreatAbsentCond extends SectorThreatPresentCond {
	
	public SectorThreatAbsentCond() {
		super();
		type = ConditionType.SECTOR_THREAT_ABSENT;
	}

}
