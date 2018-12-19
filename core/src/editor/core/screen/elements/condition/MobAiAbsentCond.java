package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class MobAiAbsentCond extends MobAiPresentCond {

	public MobAiAbsentCond() {
		super();
		type = ConditionType.MOB_AI_ABSENT;
	}

}
