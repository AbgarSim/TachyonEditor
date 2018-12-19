package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipNamePresentCond extends ShipCondition {

	public String shipName;
	
	public ShipNamePresentCond() {
		super();
		type = ConditionType.SHIP_NAME_PRESENT;
	}
	
	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("ship_name")){
			shipName = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return shipName!=null && !shipName.isEmpty();
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="ship_name="+shipName+";";
		return code;
	}

}
