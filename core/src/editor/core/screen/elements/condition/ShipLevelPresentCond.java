package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipLevelPresentCond extends ShipCondition {

	public int shipLevel; 
	
	public ShipLevelPresentCond() {
		super();
		type = ConditionType.SHIP_LEVEL_PRESENT;
	}

	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("ship_level")){
			shipLevel = Integer.parseInt(value);
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return shipLevel>0;
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="ship_level="+shipLevel+";";
		return code;
	}

}
