package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class SectorPlayersAbsentCond extends SectorPlayersPresentCond {

	public SectorPlayersAbsentCond() {
		super();
		type = ConditionType.SECTOR_PLAYERS_ABSENT;
	}

}
