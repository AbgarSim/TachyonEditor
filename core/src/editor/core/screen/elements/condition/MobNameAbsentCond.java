package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class MobNameAbsentCond extends MobNamePresentCond {

	public MobNameAbsentCond() {
		super();
		type = ConditionType.MOB_NAME_ABSENT;
	}

}
