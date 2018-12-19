package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ServerVariableAbsentCond extends ServerVariablePresentCond {

	public ServerVariableAbsentCond() {
		super();
		type = ConditionType.SERVER_VARIABLE_ABSENT;
	}

}
