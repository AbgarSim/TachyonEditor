package editor.core.screen.elements.condition;

import editor.core.enums.ConditionType;

public class ShipMissilesPresentCond extends ShipCondition {

	public int qty; 
	
	public ShipMissilesPresentCond() {
		super();
		type = ConditionType.SHIP_MISSILES_PRESENT;
	}

	@Override
	public void setParam(String key, String value){
		if(key.equalsIgnoreCase("qty")){
			qty = Integer.parseInt(value);
		}else{
			super.setParam(key, value);
		}
	}

	@Override
	public boolean conditionValid() {
		return qty>0;
	}

	@Override
	public String getCode() {
		return super.getCode()+"qty="+qty+";";
	}
	
}
