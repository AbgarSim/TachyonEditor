package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class MobHealthAbsentCond extends MobHealthPresentCond {

	public MobHealthAbsentCond() {
		super();
		type = ConditionType.MOB_HEALTH_ABSENT;
	}

}
