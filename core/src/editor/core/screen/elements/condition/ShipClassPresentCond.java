package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;
import editor.core.enums.ShipClass;

public class ShipClassPresentCond extends ShipCondition {

	public ShipClass shipClass;
	
	public ShipClassPresentCond() {
		super();
		type = ConditionType.SHIP_CLASS_PRESENT;
	}

	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("ship_class")){
			shipClass = ShipClass.valueOf(value);
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return shipClass!=null;
	}

	@Override
	public String getCode() {
		return super.getCode()+"ship_class="+shipClass.name()+";";
	}
	
}
