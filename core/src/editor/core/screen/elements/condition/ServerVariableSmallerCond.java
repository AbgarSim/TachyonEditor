package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ServerVariableSmallerCond extends ServerCondition {

	public String varName;
	public String varValue;

	public ServerVariableSmallerCond() {
		super();
		type = ConditionType.SERVER_VARIABLE_SMALLER;
	}

	@Override
	public void setParam(String key, String value) {
		if(key.equalsIgnoreCase("var_name")){
			varName = value;
		}else if(key.equalsIgnoreCase("var_value")){
			varValue = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return varName!=null && !varName.isEmpty();
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="var_name="+varName+";"+"var_value="+varValue+";";
		return code;
	}

}
