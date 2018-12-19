package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipPositionPresentCond extends ShipCondition {

	public String areaStart; 
	public String areaEnd; 
	
	public ShipPositionPresentCond() {
		super();
		type = ConditionType.SHIP_POSITION_PRESENT;
	}

	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("area_start")){
			areaStart = value;
		}else if(key.equalsIgnoreCase("area_end")){
			areaEnd = value;
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return areaStart!=null && !areaStart.isEmpty() && areaEnd!=null && !areaEnd.isEmpty();
	}

	@Override
	public String getCode() {
		String code = super.getCode();
		code+="area_start="+areaStart+";"+"area_end="+areaEnd+";";
		return code;
	}

}
