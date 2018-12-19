package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipRacePresentCond extends ShipCondition {

	public String race;
	
	public ShipRacePresentCond() {
		super();
		type = ConditionType.SHIP_RACE_PRESENT;
	}

	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("race")){
			race = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return race!=null;
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="race="+race+";";
		return code;
	}

}
