package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class MobRaceAbsentCond extends MobRacePresentCond {

	public MobRaceAbsentCond() {
		super();
		type = ConditionType.MOB_RACE_ABSENT;
	}
}
